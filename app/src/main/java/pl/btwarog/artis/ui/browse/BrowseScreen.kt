package pl.btwarog.artis.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.btwarog.artis.R
import pl.btwarog.artis.R.string
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenBrowseBinding
import pl.btwarog.artis.ui.ContentActivity
import pl.btwarog.artis.ui.browse.BrowseScreenState.ArtistsListDataLoaded
import pl.btwarog.artis.ui.browse.adapter.ArtistItemsLoadStateAdapter
import pl.btwarog.artis.ui.browse.adapter.PagingArtistItemsAdapter
import pl.btwarog.artis.ui.detail.ARG_DETAIL_ARTIST_ID
import pl.btwarog.artis.ui.utils.PopupHandler
import pl.btwarog.artis.ui.utils.QueryTextListener
import pl.btwarog.brainz.domain.error.NetworkException
import pl.btwarog.core.domain.executors.IDispatcherExecutor
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFragment
import javax.inject.Inject

@ExperimentalCoroutinesApi
class BrowseScreen :
	BaseViewModelFragment<ScreenBrowseBinding, BrowseScreenState, BrowseScreenAction, BrowseViewModel>(R.layout.screen_browse) {

	@Inject
	lateinit var iDispatcherExecutor: IDispatcherExecutor

	private lateinit var adapterPaging: PagingArtistItemsAdapter

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: BrowseViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenBrowseBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
		binding.browseErrorView.retryButton.setOnClickListener {
			adapterPaging.retry()
		}
		binding.browseSearchView.setOnQueryTextListener(QueryTextListener(lifecycleScope) { query ->
			viewModel.searchArtist(query ?: "")
		})
		initAdapter()
	}

	@OptIn(InternalCoroutinesApi::class)
	private fun initAdapter() {
		adapterPaging = PagingArtistItemsAdapter(
			{ position, artistId ->
				viewModel.onArtistClicked(position, artistId)
			},
			{ position, id, bookmarked ->
				viewModel.onArtistBookmarkClicked(position, id, bookmarked)
			}
		)
		binding.browseList.adapter = adapterPaging.withLoadStateFooter(
			ArtistItemsLoadStateAdapter { adapterPaging.retry() }
		)
		binding.browseList.itemAnimator = null
		observeStateDueToSearchChanges()
		addOverallLoadStateListener()
	}

	private fun observeStateDueToSearchChanges() {
		lifecycleScope.launch(iDispatcherExecutor.workDispatcher) {
			adapterPaging.loadStateFlow
				.distinctUntilChangedBy { state -> state.refresh }
				.filter { it.refresh is LoadState.NotLoading }
				.collect {
					withContext(iDispatcherExecutor.resultDispatcher) {
						binding.browseList.smoothScrollToPosition(0)
					}
				}
		}
	}

	private fun addOverallLoadStateListener() {
		adapterPaging.addLoadStateListener { loadState ->
			when (val state = loadState.source.refresh) {
				is NotLoading -> {
					binding.browseViewFlipper.displayedChild = CONTENT
					binding.browseProgressView.progressView.isVisible = false
				}
				is Loading -> {
					binding.browseViewFlipper.displayedChild = CONTENT
					binding.browseProgressView.progressView.isVisible = true
				}
				is Error -> {
					binding.browseProgressView.progressView.isVisible = false
					binding.browseErrorView.errorMessage.text =
						getString(if (state.error is NetworkException) string.common_network_issue else string.common_general_issue)
					binding.browseViewFlipper.displayedChild = ERROR
				}
			}
		}
	}

	override fun onResume() {
		super.onResume()
		viewModel.checkData()
	}

	override fun onScreenStateReceived(screenState: BrowseScreenState?) {
		when (screenState) {
			is ArtistsListDataLoaded -> {
				adapterPaging.submitData(
					viewLifecycleOwner.lifecycle,
					screenState.pagingData
				)
			}
		}
	}

	override fun onScreenActionReceived(screenAction: BrowseScreenAction) {
		when (screenAction) {
			is BrowseScreenAction.NavigateToDetail -> {
				(requireActivity() as ContentActivity).getActivityNavController().navigate(
					R.id.action_bottomMenu_to_detail, bundleOf(
						ARG_DETAIL_ARTIST_ID to screenAction.artistId
					)
				)
			}
			is BrowseScreenAction.BookmarkActionFinished -> {
				binding.browseProgressView.progressView.isVisible = false
				adapterPaging.onItemChanged(screenAction.position, screenAction.bookmarked)
			}
			BrowseScreenAction.BookmarkActionFailed -> {
				PopupHandler.showMessage(requireContext())
				binding.browseProgressView.progressView.isVisible = false
			}
			BrowseScreenAction.BookmarkActionLoading -> {
				binding.browseProgressView.progressView.isVisible = true
			}
		}
	}

	companion object {

		private const val CONTENT = 0
		private const val ERROR = 1
	}
}