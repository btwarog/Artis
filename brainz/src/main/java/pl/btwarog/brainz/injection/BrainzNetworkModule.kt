package pl.btwarog.brainz.injection

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import pl.btwarog.core.injection.NetworkModule
import pl.btwarog.core.network.OkHttpClientProvider
import javax.inject.Named
import javax.inject.Singleton

const val API_BASE_URL_KEY = "API_BASE_URL_KEY"

@Module(includes = [NetworkModule::class])
object BrainzNetworkModule {

	@Provides
	@Singleton
	fun provideBrainzApolloClient(
		@Named(API_BASE_URL_KEY) baseApiUrl: String,
		okHttpClientProvider: OkHttpClientProvider
	): ApolloClient {
		return ApolloClient.builder()
			.serverUrl(baseApiUrl)
			.okHttpClient(okHttpClientProvider.provide())
			.build()
	}
}