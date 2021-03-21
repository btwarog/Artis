package pl.btwarog.artis.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import pl.btwarog.artis.R

fun ImageView.loadUrlImage(url: String) {
	Glide.with(this).load(url).error(R.drawable.bg_no_image).centerCrop().into(this)
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