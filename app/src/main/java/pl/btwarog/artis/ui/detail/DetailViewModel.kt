package pl.btwarog.artis.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import pl.btwarog.artis.ui.detail.DetailScreenAction.BookmarkActionFailed
import pl.btwarog.artis.ui.detail.DetailScreenAction.BookmarkActionLoading
import pl.btwarog.artis.ui.detail.DetailScreenState.DataError
import pl.btwarog.artis.ui.detail.DetailScreenState.DataLoaded
import pl.btwarog.artis.ui.detail.DetailScreenState.DataLoading
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.error.ResultWrapper.GeneralError
import pl.btwarog.brainz.domain.error.ResultWrapper.NetworkError
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.usecase.BookmarkArtistUseCase
import pl.btwarog.brainz.domain.usecase.GetArtistDetailUseCase
import pl.btwarog.brainz.domain.usecase.UnbookmarkArtistUseCase
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import pl.btwarog.core_ui.presentation.ui.BaseViewModel
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFactory

class DetailViewModel @AssistedInject constructor(
	dispatcherExecutor: IDispatcherExecutor,
	private val getArtistDetailUseCase: GetArtistDetailUseCase,
	private val unbookmarkArtistUseCase: UnbookmarkArtistUseCase,
	private val bookmarkArtistUseCase: BookmarkArtistUseCase,
	@Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailScreenState, DetailScreenAction>(dispatcherExecutor) {

	private var artistDetailInfo: ArtistDetailInfo? = null

	init {
		loadArtist()
	}

	fun loadArtist() {
		artistDetailInfo?.let { detailInfo ->
			processScreenState(DataLoaded(detailInfo))
		} ?: run {
			savedStateHandle.get<String>(ARG_DETAIL_ARTIST_ID)?.let { artistId ->
				loadArtist(artistId)
			}
		}
	}

	private fun loadArtist(artistId: String) = work(DataLoading) {
		when (val data = getArtistDetailUseCase.getArtistDetail(artistId)) {
			is ResultWrapper.Success -> {
				artistDetailInfo = data.value
				processScreenState(DataLoaded(data.value))
			}
			GeneralError -> processScreenState(DataError(false))
			NetworkError -> processScreenState(DataError(true))
		}
	}

	fun onActionBtnClicked() {
		viewModelScope.launch(dispatcherExecutor.workDispatcher) {
			val currentArtist = artistDetailInfo
			if (currentArtist != null) {
				try {
					processScreenAction(BookmarkActionLoading)
					val bookmarked = if (currentArtist.bookmarked) {
						unbookmarkArtistUseCase.unbookmarkArtist(currentArtist.id)
						false
					} else {
						bookmarkArtistUseCase.bookmarkArtist(currentArtist.id, currentArtist)
						true
					}
					currentArtist.copy(bookmarked = bookmarked).also { artist ->
						artistDetailInfo = artist
						processScreenState(DataLoaded(artist))
					}
				} catch (exception: Exception) {
					processScreenAction(BookmarkActionFailed)
				}
			} else {
				processScreenAction(BookmarkActionFailed)
			}
		}
	}

	@AssistedFactory
	interface Factory : BaseViewModelFactory<DetailViewModel> {

		override fun create(handle: SavedStateHandle): DetailViewModel
	}
}

const val ARG_DETAIL_ARTIST_ID = "ARG_DETAIL_ARTIST_ID"

sealed class DetailScreenState : ScreenState {
	object DataLoading : DetailScreenState()
	class DataError(val networkError: Boolean) : DetailScreenState()
	class DataLoaded(val artistDetailInfo: ArtistDetailInfo) : DetailScreenState()
}

sealed class DetailScreenAction : ScreenAction {
	object BookmarkActionLoading : DetailScreenAction()
	object BookmarkActionFailed : DetailScreenAction()
}