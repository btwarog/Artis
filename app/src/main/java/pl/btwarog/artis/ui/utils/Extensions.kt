package pl.btwarog.artis.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import pl.btwarog.artis.R

fun ImageView.loadUrlImage(url: String) {
	Glide.with(this).load(url).error(R.drawable.bg_no_image).centerCrop().into(this)
}