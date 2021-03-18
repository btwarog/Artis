package pl.btwarog.artis.ui.bottommenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import pl.btwarog.artis.R

class BottomMenuScreen: Fragment(R.layout.screen_bottom_menu) {

	lateinit var bottomMenu: BottomNavigationView

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initLayouts(view)
		setBottomMenu()
	}

	private fun initLayouts(view: View) {
		bottomMenu = view.findViewById(R.id.bottomNavigationMenu)
	}

	private fun setBottomMenu() {
		val navHost = childFragmentManager.findFragmentById(R.id.bottomMenuNavHost) as NavHostFragment
		NavigationUI.setupWithNavController(bottomMenu, navHost.navController)
	}
}