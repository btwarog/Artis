package pl.btwarog.core_ui.presentation.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowCollector<T>(
	lifecycleOwner: LifecycleOwner,
	private val flow: Flow<T>,
	private val collector: (T) -> Unit
) {

	private var job: Job? = null

	init {
		lifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { source: LifecycleOwner, event: Lifecycle.Event ->
			when (event) {
				Lifecycle.Event.ON_START -> {
					job = source.lifecycleScope.launch {
						flow.collect { collector(it) }
					}
				}
				Lifecycle.Event.ON_STOP -> {
					job?.cancel()
					job = null
				}
				else -> {
				}
			}
		})
	}
}

internal fun <T> Fragment.collect(flow: Flow<T>, listener: (T) -> Unit) {
	FlowCollector(viewLifecycleOwner, flow) { item ->
		listener.invoke(item)
	}
}