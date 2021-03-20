package pl.btwarog.artis.ui.splash

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.findNavController
import pl.btwarog.artis.R
import pl.btwarog.artis.databinding.ScreenSplashBinding
import pl.btwarog.core_ui.presentation.ui.BaseFragment

class SplashScreen :
	BaseFragment<ScreenSplashBinding>(R.layout.screen_splash) {

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenSplashBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
		ObjectAnimator.ofPropertyValuesHolder(
			binding.splashLogo,
			PropertyValuesHolder.ofFloat("scaleX", SCALE_FACTOR),
			PropertyValuesHolder.ofFloat("scaleY", SCALE_FACTOR)
		).apply {
			duration = 500L
			repeatMode = ObjectAnimator.REVERSE
			repeatCount = 5
			doOnEnd {
				this@SplashScreen.findNavController().navigate(R.id.action_splash_to_bottomMenu)
			}
			start()
		}
	}

	companion object {

		private const val SCALE_FACTOR = 0.7f
	}
}