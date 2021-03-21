package pl.btwarog.artis.ui.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import pl.btwarog.artis.R
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenBookmarksBinding
import pl.btwarog.artis.ui.ContentActivity
import pl.btwarog.artis.ui.bookmarks.adapter.BookmarkedArtistItemsAdapter
import pl.btwarog.artis.ui.detail.ARG_DETAIL_ARTIST_ID
import pl.btwarog.artis.ui.utils.PopupHandler
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFragment

class BookmarksScreen :
	BaseViewModelFragment<ScreenBookmarksBinding, BookmarksScreenState, BookmarksScreenAction, BookmarksViewModel>(R.layout.screen_bookmarks) {

	private lateinit var adapter: BookmarkedArtistItemsAdapter

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: BookmarksViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenBookmarksBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
		initAdapter()
		binding.bookmarkProgressView.progressView.isVisible = false
	}

	private fun initAdapter() {
		adapter = BookmarkedArtistItemsAdapter(
			{ artistId ->
				viewModel.onArtistClicked(artistId)
			},
			{ _, id, _ ->
				viewModel.onArtistBookmarkClicked(id)
			}
		)
		binding.bookmarksList.itemAnimator = null
		binding.bookmarksList.adapter = adapter
	}

	override fun onScreenStateReceived(screenState: BookmarksScreenState?) {
		when (screenState) {
			is BookmarksScreenState.DataLoaded -> {
				adapter.submitList(screenState.data)
			}
			BookmarksScreenState.DataEmpty -> {

			}
		}
	}

	override fun onScreenActionReceived(screenAction: BookmarksScreenAction) {
		when (screenAction) {
			is BookmarksScreenAction.NavigateToDetail -> {
				(requireActivity() as ContentActivity).getActivityNavController().navigate(
					R.id.action_bottomMenu_to_detail, bundleOf(
						ARG_DETAIL_ARTIST_ID to screenAction.artistId
					)
				)
			}
			BookmarksScreenAction.BookmarkActionFailed -> {
				PopupHandler.showMessage(requireContext())
				binding.bookmarkProgressView.progressView.isVisible = false
			}
			BookmarksScreenAction.BookmarkActionLoading -> {
				binding.bookmarkProgressView.progressView.isVisible = true
			}
			BookmarksScreenAction.BookmarkActionFinished -> {
				binding.bookmarkProgressView.progressView.isVisible = false
			}
		}
	}
}