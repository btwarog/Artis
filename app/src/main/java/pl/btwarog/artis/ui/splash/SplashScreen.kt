package pl.btwarog.artis.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import pl.btwarog.artis.R
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenSplashBinding
import pl.btwarog.core.presentation.ui.BaseViewModelFragment

class SplashScreen :
	BaseViewModelFragment<ScreenSplashBinding, SplashScreenState, SplashScreenAction, SplashViewModel>(R.layout.screen_splash) {

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: SplashViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenSplashBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
	}

	override fun onScreenStateReceived(screenState: SplashScreenState?) {}

	override fun onScreenActionReceived(screenAction: SplashScreenAction) {
		if (screenAction is SplashScreenAction.NavigateToBrowse) {
			findNavController().navigate(R.id.action_splash_to_bottomMenu)
		}
	}
}