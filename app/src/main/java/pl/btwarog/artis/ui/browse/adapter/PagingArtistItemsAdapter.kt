package pl.btwarog.artis.ui.browse.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import pl.btwarog.artis.ui.common.adapter.ArtistItemsViewHolder
import pl.btwarog.artis.ui.common.adapter.ArtistItemsViewHolder.Companion.ITEM_COMPARATOR
import pl.btwarog.brainz.domain.model.IArtistListInfo

class PagingArtistItemsAdapter(
	private val onItemClickedListener: (Int, String) -> Unit,
	private val onBookmarkClickedListener: (Int, String, Boolean) -> Unit
) : PagingDataAdapter<IArtistListInfo, ArtistItemsViewHolder>(ITEM_COMPARATOR) {

	override fun getItemViewType(position: Int): Int {
		return if (position == itemCount) CONTENT_ITEM else LOADING_ITEM
	}

	override fun onBindViewHolder(holder: ArtistItemsViewHolder, position: Int) {
		getItem(position)?.let { artistInfo ->
			holder.bind(position, artistInfo, onItemClickedListener, onBookmarkClickedListener)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistItemsViewHolder {
		return ArtistItemsViewHolder.create(parent)
	}

	fun onItemChanged(position: Int, bookmarked: Boolean) {
		getItem(position)?.let {
			it.bookmarked = bookmarked
		}
		notifyItemChanged(position)
	}

	companion object {

		const val CONTENT_ITEM = 0
		const val LOADING_ITEM = 1
	}
}