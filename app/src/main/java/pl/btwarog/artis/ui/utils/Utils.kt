package pl.btwarog.artis.ui.utils

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import pl.btwarog.artis.R
import pl.btwarog.artis.ui.glide.GlideApp

fun ImageView.loadUrlImage(url: String) {
	GlideApp.with(this)
		.load(url)
		.diskCacheStrategy(DiskCacheStrategy.ALL)
		.centerCrop()
		.dontAnimate()
		.error(R.drawable.bg_no_image)
		.into(this)
}

fun getBookmarkIconDrawable(bookmarked: Boolean) =
	if (bookmarked) {
		R.drawable.ic_bookmarked
	} else {
		R.drawable.ic_not_bookmarked
	}

fun getBookmarkActionIconDescription(bookmarked: Boolean) =
	if (bookmarked) {
		R.string.content_description_unbookmark_button
	} else {
		R.string.content_description_bookmark_button
	}