package pl.btwarog.artis.ui.browse

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.btwarog.artis.ui.browse.BrowseScreenAction.BookmarkActionFailed
import pl.btwarog.artis.ui.browse.BrowseScreenAction.BookmarkActionFinished
import pl.btwarog.artis.ui.browse.BrowseScreenAction.BookmarkActionLoading
import pl.btwarog.artis.ui.browse.BrowseScreenAction.NavigateToDetail
import pl.btwarog.artis.ui.browse.BrowseScreenState.ArtistsListDataLoaded
import pl.btwarog.brainz.domain.model.IArtistListInfo
import pl.btwarog.brainz.domain.usecase.BookmarkArtistUseCase
import pl.btwarog.brainz.domain.usecase.UnbookmarkArtistUseCase
import pl.btwarog.brainz.domain.usecase.VerifyArtistBookmarkedUseCase
import pl.btwarog.brainz.presentation.paging.factory.ArtistListDataFactory
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import pl.btwarog.core_ui.presentation.ui.BaseViewModel
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFactory

@ExperimentalCoroutinesApi
class BrowseViewModel @AssistedInject constructor(
	dispatcherExecutor: IDispatcherExecutor,
	private val artistListDataFactory: ArtistListDataFactory,
	private val bookmarkArtistUseCase: BookmarkArtistUseCase,
	private val unbookmarkArtistUseCase: UnbookmarkArtistUseCase,
	private val verifyArtistBookmarkedUseCase: VerifyArtistBookmarkedUseCase,
	@Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<BrowseScreenState, BrowseScreenAction>(dispatcherExecutor) {

	private var pagingJob: Job? = null
	private var forceRefresh = false

	init {
		val searchedQuery = savedStateHandle.get<String>(ARG_SEARCHED_QUERY) ?: DEFAULT_SEARCH
		searchArtist(searchedQuery, true)
	}

	fun searchArtist(query: String, force: Boolean = false) {
		val newQuery = if (query.isEmpty()) {
			DEFAULT_SEARCH
		} else {
			query
		}
		val lastQuery = savedStateHandle.get<String>(ARG_SEARCHED_QUERY)
		if (lastQuery != newQuery || force) {
			savedStateHandle[ARG_SEARCHED_QUERY] = newQuery
			pagingJob?.cancel()
			pagingJob = viewModelScope.launch {
				artistListDataFactory.create(newQuery).cachedIn(viewModelScope).collect { data ->
					withContext(dispatcherExecutor.resultDispatcher) {
						processScreenState(ArtistsListDataLoaded(data))
					}
				}
			}
		}
	}

	fun onArtistClicked(position: Int, artistId: String) {
		forceRefresh = true
		savedStateHandle[ARG_ITEM_ID] = artistId
		savedStateHandle[ARG_ITEM_POSITION] = position
		processScreenAction(NavigateToDetail(artistId))
	}

	fun onArtistBookmarkClicked(position: Int, artistId: String, bookmarked: Boolean) {
		if (bookmarked) {
			unbookmarkArtist(position, artistId)
		} else {
			bookmarkArtist(position, artistId)
		}
	}

	private fun unbookmarkArtist(position: Int, artistId: String) = work {
		processScreenAction(BookmarkActionLoading)
		try {
			unbookmarkArtistUseCase.unbookmarkArtist(artistId)
			processScreenAction(BookmarkActionFinished(position, false))
		} catch (exception: Exception) {
			processScreenAction(BookmarkActionFailed)
		}
	}

	private fun bookmarkArtist(position: Int, artistId: String) = work {
		processScreenAction(BookmarkActionLoading)
		try {
			bookmarkArtistUseCase.bookmarkArtist(artistId, null)
			processScreenAction(BookmarkActionFinished(position, true))
		} catch (exception: Exception) {
			processScreenAction(BookmarkActionFailed)
		}
	}

	fun checkData() {
		val artistId = savedStateHandle.get<String>(ARG_ITEM_ID) ?: ""
		val artistPosition = savedStateHandle.get<Int>(ARG_ITEM_POSITION) ?: -1
		if (forceRefresh && artistId.isNotEmpty() && artistPosition != -1) {
			forceRefresh = false
			viewModelScope.launch(dispatcherExecutor.workDispatcher) {
				processScreenAction(BookmarkActionLoading)
				val bookmarked = verifyArtistBookmarkedUseCase.verifyArtist(artistId)
				savedStateHandle[ARG_ITEM_ID] = ""
				savedStateHandle[ARG_ITEM_POSITION] = -1
				processScreenAction(BookmarkActionFinished(artistPosition, bookmarked))
			}
		}
	}

	@AssistedFactory
	interface Factory : BaseViewModelFactory<BrowseViewModel> {

		override fun create(handle: SavedStateHandle): BrowseViewModel
	}

	companion object {

		private const val DEFAULT_SEARCH = "*"
	}
}

const val ARG_SEARCHED_QUERY = "ARG_SEARCHED_QUERY"
const val ARG_ITEM_POSITION = "ARG_ITEM_POSITION"
const val ARG_ITEM_ID = "ARG_ITEM_ID"

sealed class BrowseScreenState : ScreenState {
	class ArtistsListDataLoaded(val pagingData: PagingData<IArtistListInfo>) : BrowseScreenState()
}

sealed class BrowseScreenAction : ScreenAction {
	class NavigateToDetail(val artistId: String) : BrowseScreenAction()
	object BookmarkActionLoading : BrowseScreenAction()
	object BookmarkActionFailed : BrowseScreenAction()
	class BookmarkActionFinished(val position: Int, val bookmarked: Boolean) : BrowseScreenAction()
}