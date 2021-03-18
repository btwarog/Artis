package pl.btwarog.artis.ui.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import pl.btwarog.artis.R
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenBookmarksBinding
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFragment

class BookmarksScreen :
	BaseViewModelFragment<ScreenBookmarksBinding, BookmarksScreenState, BookmarksScreenAction, BookmarksViewModel>(R.layout.screen_bookmarks) {

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: BookmarksViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenBookmarksBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
	}

	override fun onScreenStateReceived(screenState: BookmarksScreenState?) {}

	override fun onScreenActionReceived(screenAction: BookmarksScreenAction) {}
}