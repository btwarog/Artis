package pl.btwarog.artis.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.btwarog.artis.R
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenBrowseBinding
import pl.btwarog.artis.ui.ContentActivity
import pl.btwarog.artis.ui.detail.ARG_DETAIL_ARTIST_ID
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFragment

@ExperimentalCoroutinesApi
class BrowseScreen :
	BaseViewModelFragment<ScreenBrowseBinding, BrowseScreenState, BrowseScreenAction, BrowseViewModel>(R.layout.screen_browse) {

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: BrowseViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenBrowseBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
		binding.detailActionBtn.setOnClickListener {
			viewModel.onArtistClicked("artist_id_test")
		}
	}

	override fun onScreenStateReceived(screenState: BrowseScreenState?) {}

	override fun onScreenActionReceived(screenAction: BrowseScreenAction) {
		if (screenAction is BrowseScreenAction.NavigateToDetail) {
			(requireActivity() as ContentActivity).getActivityNavController().navigate(
				R.id.action_bottomMenu_to_detail, bundleOf(
					ARG_DETAIL_ARTIST_ID to screenAction.artistId
				)
			)
		}
	}
}