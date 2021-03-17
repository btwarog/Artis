package pl.btwarog.artis.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import pl.btwarog.artis.R

class SplashScreen: Fragment(R.layout.screen_splash) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		lifecycleScope.launchWhenCreated {
			delay(1500L)
			withContext(Dispatchers.Main) {
				findNavController().navigate(R.id.action_splash_to_bottomMenu)
			}
		}
	}
}