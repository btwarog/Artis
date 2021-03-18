package pl.btwarog.artis.ui.splash

import kotlinx.coroutines.delay
import pl.btwarog.artis.ui.splash.SplashScreenAction.NavigateToBrowse
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core.presentation.model.ScreenAction
import pl.btwarog.core.presentation.model.ScreenState
import pl.btwarog.core.presentation.ui.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
	dispatcherExecutor: IDispatcherExecutor,
) : BaseViewModel<SplashScreenState, SplashScreenAction>(dispatcherExecutor) {

	init {
		delayNavigation()
	}

	private fun delayNavigation() = work {
		delay(2500L)
		processScreenAction(NavigateToBrowse)
	}
}

sealed class SplashScreenState : ScreenState

sealed class SplashScreenAction : ScreenAction {
	object NavigateToBrowse : SplashScreenAction()
}