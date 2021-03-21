package pl.btwarog.artis.ui.browse.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import pl.btwarog.brainz.domain.model.ArtistBasicInfo

class ArtistItemsAdapter(
	private val onItemClickedListener: (String) -> Unit,
	private val onBookmarkClickedListener: (Int, String, Boolean) -> Unit
) :
	PagingDataAdapter<ArtistBasicInfo, ArtistItemsViewHolder>(ITEM_COMPARATOR) {

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

		private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<ArtistBasicInfo>() {
			override fun areItemsTheSame(oldItem: ArtistBasicInfo, newItem: ArtistBasicInfo) = oldItem.id == newItem.id

			override fun areContentsTheSame(oldItem: ArtistBasicInfo, newItem: ArtistBasicInfo) = oldItem == newItem
		}
	}
}