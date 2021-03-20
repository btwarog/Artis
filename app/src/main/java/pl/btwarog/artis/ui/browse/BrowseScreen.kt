package pl.btwarog.artis.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.btwarog.artis.R
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenBrowseBinding
import pl.btwarog.artis.ui.ContentActivity
import pl.btwarog.artis.ui.browse.adapter.ArtistItemsAdapter
import pl.btwarog.artis.ui.browse.adapter.ArtistItemsLoadStateAdapter
import pl.btwarog.artis.ui.detail.ARG_DETAIL_ARTIST_ID
import pl.btwarog.brainz.domain.error.NetworkException
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFragment

@ExperimentalCoroutinesApi
class BrowseScreen :
	BaseViewModelFragment<ScreenBrowseBinding, BrowseScreenState, BrowseScreenAction, BrowseViewModel>(R.layout.screen_browse) {

	private lateinit var adapter: ArtistItemsAdapter

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: BrowseViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenBrowseBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
		binding.browseErrorView.retryButton.setOnClickListener {
			adapter.retry()
		}
		initAdapter()
	}

	private fun initAdapter() {
		adapter = ArtistItemsAdapter { artistId ->
			viewModel.onArtistClicked(artistId)
		}
		binding.browseList.itemAnimator = null
		binding.browseList.adapter = adapter.withLoadStateFooter(
			ArtistItemsLoadStateAdapter { adapter.retry() }
		)
		adapter.addLoadStateListener { loadState ->
			when (val state = loadState.source.refresh) {
				is LoadState.NotLoading -> binding.browseViewFlipper.displayedChild = 0
				is LoadState.Loading -> binding.browseViewFlipper.displayedChild = 1
				is LoadState.Error -> {
					binding.browseErrorView.errorMessage.text =
						getString(if (state.error is NetworkException) R.string.common_network_issue else R.string.common_general_issue)
					binding.browseViewFlipper.displayedChild = 2
				}
			}
		}
	}

	override fun onScreenStateReceived(screenState: BrowseScreenState?) {
		if (screenState is BrowseScreenState.ArtistsListDataLoaded) {
			adapter.submitData(
				viewLifecycleOwner.lifecycle,
				screenState.pagingData
			)
		}
	}

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