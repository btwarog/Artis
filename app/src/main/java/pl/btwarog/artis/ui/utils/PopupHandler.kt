package pl.btwarog.artis.ui.utils

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.btwarog.artis.R

object PopupHandler {

	fun showMessage(
		context: Context,
		title: String = context.getString(R.string.common_error_title),
		message: String = context.getString(R.string.common_general_issue),
		positiveButton: String = context.getString(R.string.common_ok),
		positiveButtonListener: (() -> Unit)? = null
	) {
		MaterialAlertDialogBuilder(context)
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positiveButton)
			{ d: DialogInterface, _: Int ->
				positiveButtonListener?.invoke()
				d.dismiss()
			}
			.create().apply {
				show()
			}
	}
}