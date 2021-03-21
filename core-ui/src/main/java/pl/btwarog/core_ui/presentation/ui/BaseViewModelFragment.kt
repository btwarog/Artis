package pl.btwarog.core_ui.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import pl.btwarog.core_ui.presentation.model.ScreenAction
import pl.btwarog.core_ui.presentation.model.ScreenState
import javax.inject.Inject

abstract class BaseViewModelFragment<VB : ViewBinding, STATE : ScreenState, ACTION : ScreenAction, VM : BaseViewModel<STATE, ACTION>>(
	@LayoutRes layoutId: Int
) : BaseFragment<VB>(layoutId) {

	@Inject
	lateinit var defaultViewModelFactory: dagger.Lazy<AppViewModelFactory>

	protected abstract val viewModel: VM

	override fun onAttach(context: Context) {
		inject()
		super.onAttach(context)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		collect(viewModel.screenState) { state ->
			onScreenStateReceived(state)
		}
		collect(viewModel.screenAction) { action ->
			onScreenActionReceived(action)
		}
	}

	override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory =
		defaultViewModelFactory.get().create(this, arguments)

	protected abstract fun inject()
	protected abstract fun onScreenStateReceived(screenState: STATE?)
	protected abstract fun onScreenActionReceived(screenAction: ACTION)
}