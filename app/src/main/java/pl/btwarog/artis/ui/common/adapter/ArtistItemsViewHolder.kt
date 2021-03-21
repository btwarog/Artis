package pl.btwarog.artis.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.btwarog.artis.R
import pl.btwarog.artis.databinding.ItemArtistBinding
import pl.btwarog.artis.ui.utils.getBookmarkActionIconDescription
import pl.btwarog.artis.ui.utils.getBookmarkIconDrawable
import pl.btwarog.artis.ui.utils.loadUrlImage
import pl.btwarog.brainz.domain.model.IArtistListInfo

class ArtistItemsViewHolder(private val binding: ItemArtistBinding) :
	RecyclerView.ViewHolder(binding.root) {

	fun bind(
		position: Int,
		artistInfo: IArtistListInfo,
		onItemClickedListener: (Int, String) -> Unit,
		onBookmarkClickedListener: (Int, String, Boolean) -> Unit
	) {
		with(binding) {
			artistName.text = artistInfo.name
			artistDisambiguation.text = artistInfo.disambiguation
			with(artistInfo.bookmarked) {
				artistAction.setImageDrawable(
					ContextCompat.getDrawable(
						artistCardImage.context,
						getBookmarkIconDrawable(this)
					)
				)
				artistAction.contentDescription =
					artistAction.context.getString(getBookmarkActionIconDescription(this))
				artistAction.setOnClickListener {
					onBookmarkClickedListener(position, artistInfo.id, artistInfo.bookmarked)
				}
			}
			artistDisambiguation.text = artistInfo.disambiguation
			artistCardImage.loadUrlImage(artistInfo.getArtistImageUrl())
		}
		binding.root.setOnClickListener {
			onItemClickedListener(position, artistInfo.id)
		}
	}

	companion object {

		fun create(parent: ViewGroup): ArtistItemsViewHolder {
			val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.item_artist, parent, false)
			val binding = ItemArtistBinding.bind(view)
			return ArtistItemsViewHolder(binding)
		}

		val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<IArtistListInfo>() {
			override fun areItemsTheSame(oldItem: IArtistListInfo, newItem: IArtistListInfo) = oldItem.id == newItem.id

			override fun areContentsTheSame(oldItem: IArtistListInfo, newItem: IArtistListInfo) =
				oldItem.id == newItem.id && oldItem.bookmarked == newItem.bookmarked
		}
	}
}