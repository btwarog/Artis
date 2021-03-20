package pl.btwarog.artis.ui.browse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import pl.btwarog.artis.R
import pl.btwarog.artis.databinding.ItemArtistLoadStateBinding

class ArtistItemsLoadStateViewHolder(
	private val binding: ItemArtistLoadStateBinding,
	retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

	init {
		binding.retryButton.setOnClickListener { retry.invoke() }
	}

	fun bind(loadState: LoadState) {
		binding.progressBar.isVisible = loadState is LoadState.Loading
		binding.retryButton.isInvisible = loadState is LoadState.Loading
	}

	companion object {

		fun create(parent: ViewGroup, retry: () -> Unit): ArtistItemsLoadStateViewHolder {
			val view = LayoutInflater.from(parent.context)
				.inflate(R.layout.item_artist_load_state, parent, false)
			val binding = ItemArtistLoadStateBinding.bind(view)
			return ArtistItemsLoadStateViewHolder(binding, retry)
		}
	}
}