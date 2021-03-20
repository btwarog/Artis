package pl.btwarog.artis.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrlImage(url: String) {
	Glide.with(this).load(url).centerCrop().into(this)
}