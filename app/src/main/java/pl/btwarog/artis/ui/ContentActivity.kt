package pl.btwarog.artis.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import pl.btwarog.artis.R

class ContentActivity : AppCompatActivity(R.layout.activity_content) {

	fun getActivityNavController() = findNavController(R.id.appContent)
}