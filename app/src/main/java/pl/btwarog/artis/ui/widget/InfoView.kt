package pl.btwarog.artis.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import pl.btwarog.artis.R

class InfoView : LinearLayout {

	private lateinit var title: TextView
	private lateinit var value: TextView

	constructor(context: Context) : super(context) {
		initView(null)
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		initView(attrs)
	}

	constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		initView(attrs)
	}

	private fun initView(attrs: AttributeSet?) {
		orientation = VERTICAL
		View.inflate(context, R.layout.layout_info_view, this)

		title = findViewById(R.id.infoTitle)
		value = findViewById(R.id.infoValue)

		attrs?.let {
			val a = context.obtainStyledAttributes(attrs, R.styleable.InfoView, 0, 0)
			if (a.hasValue(R.styleable.InfoView_title)) {
				setTitle(a.getString(R.styleable.InfoView_title))
			}
			a.recycle()
		}
	}

	private fun setTitle(newTitle: String?) {
		title.text = newTitle
	}

	fun setValue(newValue: String) {
		value.text = newValue
	}
}