package pl.btwarog.artis.ui.detail

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import pl.btwarog.artis.ui.detail.DetailScreenState.DataError
import pl.btwarog.artis.ui.detail.DetailScreenState.DataLoaded
import pl.btwarog.artis.ui.detail.DetailScreenState.DataLoading
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.error.ResultWrapper.GeneralError
import pl.btwarog.brainz.domain.error.ResultWrapper.NetworkError
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.usecase.GetArtistDetailUseCase
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import pl.btwarog.core_ui.presentation.ui.BaseViewModel
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFactory

class DetailViewModel @AssistedInject constructor(
	dispatcherExecutor: IDispatcherExecutor,
	private val getArtistDetailUseCase: GetArtistDetailUseCase,
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

sealed class DetailScreenAction : ScreenAction