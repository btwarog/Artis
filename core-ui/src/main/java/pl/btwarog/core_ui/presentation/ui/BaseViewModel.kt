package pl.btwarog.core_ui.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState

open class BaseViewModel<STATE : ScreenState, SCREEN_ACTION : ScreenAction>(private val dispatcherExecutor: IDispatcherExecutor) :
	ViewModel() {

	private val _screenState = MutableStateFlow<STATE?>(null)
	internal val screenState: StateFlow<STATE?>
		get() = _screenState

	private val _screenAction = Channel<SCREEN_ACTION>(Channel.BUFFERED)
	internal val screenAction: Flow<SCREEN_ACTION>
		get() = _screenAction.receiveAsFlow()

	private var currentWork: Job? = null

	protected fun processScreenState(currentState: STATE) {
		viewModelScope.launch(dispatcherExecutor.resultDispatcher) {
			_screenState.value = currentState
		}
	}

	protected fun processScreenAction(action: SCREEN_ACTION) {
		viewModelScope.launch(dispatcherExecutor.resultDispatcher) {
			_screenAction.send(action)
		}
	}

	protected fun work(loadingState: STATE? = null, workBlock: suspend () -> Unit) {
		if (!screenIdle()) {
			return
		}
		loadingState?.let { state ->
			processScreenState(state)
		}
		currentWork = viewModelScope.launch(dispatcherExecutor.workDispatcher) {
			workBlock.invoke()
		}
	}

	private fun screenIdle(): Boolean {
		return currentWork == null || currentWork?.isActive != false || currentWork?.isCompleted == true
	}
}