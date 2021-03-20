package pl.btwarog.artis.ui.browse.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ArtistItemsLoadStateAdapter(private val retry: () -> Unit) :
	LoadStateAdapter<ArtistItemsLoadStateViewHolder>() {

	override fun onBindViewHolder(holder: ArtistItemsLoadStateViewHolder, loadState: LoadState) {
		holder.bind(loadState)
	}

	override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ArtistItemsLoadStateViewHolder {
		return ArtistItemsLoadStateViewHolder.create(parent, retry)
	}
}