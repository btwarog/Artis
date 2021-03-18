package pl.btwarog.core.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import pl.btwarog.core.injection.LOG_ENABLED_KEY
import javax.inject.Inject
import javax.inject.Named

class LoggingInterceptorProvider @Inject constructor(@Named(LOG_ENABLED_KEY) private val logEnabled: Boolean) {

	fun provide(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply {
			level = if (logEnabled) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
		}
	}
}