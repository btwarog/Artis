package pl.btwarog.artis.ui.browse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pl.btwarog.artis.R
import pl.btwarog.artis.databinding.ItemArtistBinding
import pl.btwarog.artis.ui.utils.loadUrlImage
import pl.btwarog.brainz.domain.model.ArtistBasicInfo

class ArtistItemsViewHolder(private val binding: ItemArtistBinding) :
	RecyclerView.ViewHolder(binding.root) {

	fun bind(artistInfo: ArtistBasicInfo, onItemClickedListener: (String) -> Unit) {
		with(binding) {
			artistName.text = artistInfo.name
			artistDisambiguation.text = artistInfo.disambiguation
			with(artistInfo.bookmarked) {
				artistAction.setImageDrawable(
					ContextCompat.getDrawable(
						artistCardImage.context,
						getBookmarkActionIcon(this)
					)
				)
				artistAction.contentDescription =
					artistAction.context.getString(getBookmarkActionIconDescription(this))
			}
			artistDisambiguation.text = artistInfo.disambiguation
			artistCardImage.loadUrlImage(artistInfo.getArtistImageUrl())
		}
		binding.root.setOnClickListener {
			onItemClickedListener(artistInfo.id)
		}
	}

	private fun getBookmarkActionIcon(bookmarked: Boolean) =
		if (bookmarked) {
			R.drawable.ic_bookmarked
		} else {
			R.drawable.ic_not_bookmarked
		}

	private fun getBookmarkActionIconDescription(bookmarked: Boolean) =
		if (bookmarked) {
			R.string.content_description_unbookmark_button
		} else {
			R.string.content_description_bookmark_button
		}

	companion object {

		fun create(parent: ViewGroup): ArtistItemsViewHolder {
			val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.item_artist, parent, false)
			val binding = ItemArtistBinding.bind(view)
			return ArtistItemsViewHolder(binding)
		}
	}
}