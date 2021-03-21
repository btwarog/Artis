package pl.btwarog.artis.ui.bookmarks.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import pl.btwarog.artis.ui.common.adapter.ArtistItemsViewHolder
import pl.btwarog.artis.ui.common.adapter.ArtistItemsViewHolder.Companion.ITEM_COMPARATOR
import pl.btwarog.brainz.domain.model.IArtistListInfo

class BookmarkedArtistItemsAdapter(
	private val onItemClickedListener: (Int, String) -> Unit,
	private val onBookmarkClickedListener: (Int, String, Boolean) -> Unit
) : ListAdapter<IArtistListInfo, ArtistItemsViewHolder>(ITEM_COMPARATOR) {

	override fun onBindViewHolder(holder: ArtistItemsViewHolder, position: Int) {
		getItem(position)?.let { artistInfo ->
			holder.bind(position, artistInfo, onItemClickedListener, onBookmarkClickedListener)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistItemsViewHolder {
		return ArtistItemsViewHolder.create(parent)
	}
}