package pl.btwarog.artis.ui.bottommenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.github.ajalt.timberkt.d
import pl.btwarog.artis.R
import pl.btwarog.artis.databinding.ScreenBottomMenuBinding
import pl.btwarog.core_ui.presentation.ui.BaseFragment

class BottomMenuScreen : BaseFragment<ScreenBottomMenuBinding>(R.layout.screen_bottom_menu) {

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenBottomMenuBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
		setBottomMenu()
	}

	private fun setBottomMenu() {
		val navHost = childFragmentManager.findFragmentById(R.id.bottomMenuNavHost) as NavHostFragment
		NavigationUI.setupWithNavController(binding.bottomNavigationMenu, navHost.navController)
		binding.bottomNavigationMenu.setOnNavigationItemReselectedListener {
			d { "Prevent reloading fragment" }
		}
	}
}