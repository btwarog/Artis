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
import pl.btwarog.artis.ui.browse.BrowseScreenAction.NavigateToDetail
import pl.btwarog.artis.ui.browse.BrowseScreenState.ArtistsListDataLoaded
import pl.btwarog.artis.ui.browse.BrowseScreenState.ArtistsListInfo
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
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
	@Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<BrowseScreenState, BrowseScreenAction>(dispatcherExecutor) {

	var pagingJob: Job? = null

	init {
		val searchedQuery = savedStateHandle.get<String>(ARG_SEARCHED_QUERY)
		if (searchedQuery != null) {
			if (searchedQuery.isNotEmpty()) {
				searchArtist(searchedQuery)
			} else {
				processScreenState(ArtistsListInfo(false))
			}
		} else {
			processScreenState(ArtistsListInfo(true))
		}
	}

	fun searchArtist(query: String) {
		savedStateHandle[ARG_SEARCHED_QUERY] = query
		pagingJob?.cancel()
		pagingJob = viewModelScope.launch {
			artistListDataFactory.create(query).cachedIn(viewModelScope).collect { data ->
				withContext(dispatcherExecutor.resultDispatcher) {
					processScreenState(ArtistsListDataLoaded(data))
				}
			}
		}
	}

	fun onArtistClicked(artistId: String) {
		processScreenAction(NavigateToDetail(artistId))
	}

	@AssistedFactory
	interface Factory : BaseViewModelFactory<BrowseViewModel> {

		override fun create(handle: SavedStateHandle): BrowseViewModel
	}
}

const val ARG_SEARCHED_QUERY = "ARG_SEARCHED_QUERY"

sealed class BrowseScreenState : ScreenState {
	class ArtistsListInfo(val expanded: Boolean) : BrowseScreenState()
	class ArtistsListDataLoaded(val pagingData: PagingData<ArtistBasicInfo>) : BrowseScreenState()
}

sealed class BrowseScreenAction : ScreenAction {
	class NavigateToDetail(val artistId: String) : BrowseScreenAction()
}