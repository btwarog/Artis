package pl.btwarog.core.presentation.model

internal class ScreenAction<out T>(private val action: T) {

	var hasBeenConsumed = false
		private set

	fun getActionIfNotConsumed(): T? {
		return if (hasBeenConsumed) {
			null
		} else {
			hasBeenConsumed = true
			action
		}
	}
}