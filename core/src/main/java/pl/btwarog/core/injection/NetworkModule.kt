package pl.btwarog.core.injection

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import pl.btwarog.core.network.OkHttpClientProvider
import pl.btwarog.core.network.interceptor.LoggingInterceptorProvider
import javax.inject.Singleton

const val LOG_ENABLED_KEY = "LOG_ENABLED_KEY"

@Module
object NetworkModule {

	@Singleton
	@Provides
	fun provideInterceptors(
		loggingInterceptor: LoggingInterceptorProvider
	): MutableList<Interceptor> {
		return mutableListOf(loggingInterceptor.provide())
	}

	@Singleton
	@Provides
	fun provideOkHttpClient(okHttpClientProvider: OkHttpClientProvider): OkHttpClient {
		return okHttpClientProvider.provide()
	}
}