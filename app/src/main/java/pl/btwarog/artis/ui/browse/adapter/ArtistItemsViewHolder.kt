package pl.btwarog.artis.ui.browse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.btwarog.artis.R
import pl.btwarog.artis.databinding.ItemArtistBinding
import pl.btwarog.brainz.domain.model.ArtistBasicInfo

class ArtistItemsViewHolder(private val binding: ItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(artistInfo: ArtistBasicInfo, onItemClickedListener: (String) -> Unit) {
		with(binding) {
			artistName.text = artistInfo.name
		}
		binding.root.setOnClickListener {
			onItemClickedListener(artistInfo.id)
		}
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