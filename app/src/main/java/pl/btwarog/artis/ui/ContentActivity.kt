package pl.btwarog.artis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.btwarog.artis.R.layout

class ContentActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(layout.activity_content)
	}
}