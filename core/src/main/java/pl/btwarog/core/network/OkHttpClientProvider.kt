package pl.btwarog.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OkHttpClientProvider @Inject constructor(private val interceptors: MutableList<Interceptor>) {

	fun provide(): OkHttpClient {
		val builder = OkHttpClient.Builder()
			.connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)

		interceptors.forEach { builder.addInterceptor(it) }

		builder.cache(null)
		return builder.build()
	}
}