package pl.btwarog.artis.ui.utils

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QueryTextListener(
	private val coroutineScope: CoroutineScope,
	private val onTextChange: (String?) -> Unit
) : SearchView.OnQueryTextListener {

	private var queryJob: Job? = null

	override fun onQueryTextSubmit(query: String?) = false

	override fun onQueryTextChange(newText: String?): Boolean {
		queryJob?.cancel()
		queryJob = coroutineScope.launch {
			newText?.let { newQuery ->
				delay(DEBOUNCE_INTERVAL)
				onTextChange(newQuery)
			}
		}
		return false
	}

	companion object {

		private const val DEBOUNCE_INTERVAL = 500L
	}
}