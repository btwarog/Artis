package pl.btwarog.artis.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import pl.btwarog.artis.R
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenDetailBinding
import pl.btwarog.core.presentation.ui.BaseViewModelFragment

class DetailScreen :
	BaseViewModelFragment<ScreenDetailBinding, DetailScreenState, DetailScreenAction, DetailViewModel>(R.layout.screen_detail) {

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: DetailViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenDetailBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
	}

	override fun onScreenStateReceived(screenState: DetailScreenState?) {}

	override fun onScreenActionReceived(screenAction: DetailScreenAction) {}
}