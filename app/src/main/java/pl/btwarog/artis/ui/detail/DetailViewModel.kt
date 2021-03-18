package pl.btwarog.artis.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.github.ajalt.timberkt.d
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core.presentation.model.ScreenAction
import pl.btwarog.core.presentation.model.ScreenState
import pl.btwarog.core.presentation.ui.BaseViewModel
import pl.btwarog.core.presentation.ui.BaseViewModelFactory

class DetailViewModel @AssistedInject constructor(
	dispatcherExecutor: IDispatcherExecutor,
	@Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailScreenState, DetailScreenAction>(dispatcherExecutor) {

	init {
		savedStateHandle.get<String>(ARG_DETAIL_ARTIST_ID)?.let { message ->
			d { message }
		}
	}

	@AssistedFactory
	interface Factory : BaseViewModelFactory<DetailViewModel> {

		override fun create(handle: SavedStateHandle): DetailViewModel
	}
}

const val ARG_DETAIL_ARTIST_ID = "ARG_DETAIL_ARTIST_ID"

sealed class DetailScreenState : ScreenState

sealed class DetailScreenAction : ScreenAction