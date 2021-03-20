package pl.btwarog.brainz.injection

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import pl.btwarog.brainz.domain.ArtistsRemote
import pl.btwarog.brainz.domain.ArtistsRepository
import pl.btwarog.brainz.domain.mapper.ArtistDetailInfoRemoteMapper
import pl.btwarog.brainz.domain.mapper.PaginatedArtistsListRemoteMapper
import pl.btwarog.brainz.domain.remote.IArtistsRemote
import pl.btwarog.brainz.domain.repository.IArtistsRepository
import javax.inject.Singleton

@Module
object BrainzDataModule {

	@Provides
	@Singleton
	fun provideArtistRepository(
		artistsRemote: IArtistsRemote
	): IArtistsRepository {
		return ArtistsRepository(artistsRemote)
	}

	@Provides
	@Singleton
	fun provideArtistRemote(
		apolloClient: ApolloClient,
		paginatedArtistsListRemoteMapper: PaginatedArtistsListRemoteMapper,
		artistDetailInfoRemoteMapper: ArtistDetailInfoRemoteMapper
	): IArtistsRemote {
		return ArtistsRemote(apolloClient, paginatedArtistsListRemoteMapper, artistDetailInfoRemoteMapper)
	}
}