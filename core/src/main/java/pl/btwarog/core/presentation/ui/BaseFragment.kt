package pl.btwarog.core.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import pl.btwarog.core.presentation.model.ScreenAction
import pl.btwarog.core.presentation.model.ScreenState
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, STATE : ScreenState, ACTION : ScreenAction, VM : BaseViewModel<STATE, ACTION>>(
	@LayoutRes layoutId: Int
) : Fragment(layoutId) {

	@Inject
	lateinit var defaultViewModelFactory: dagger.Lazy<AppViewModelFactory>

	protected lateinit var binding: VB

	protected abstract val viewModel: VM

	override fun onAttach(context: Context) {
		inject()
		super.onAttach(context)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = getBinding(inflater, container)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView(savedInstanceState)

		collect(viewModel.screenState) { state ->
			onScreenStateReceived(state)
		}
		collect(viewModel.screenAction) { action ->
			onScreenActionReceived(action)
		}
	}

	override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory = defaultViewModelFactory.get().create(this, arguments)

	protected abstract fun inject()
	protected abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB
	protected abstract fun initView(savedInstanceState: Bundle?)
	protected abstract fun onScreenStateReceived(screenState: STATE?)
	protected abstract fun onScreenActionReceived(screenAction: ACTION)
}