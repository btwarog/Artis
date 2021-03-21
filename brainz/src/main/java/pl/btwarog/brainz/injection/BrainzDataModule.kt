package pl.btwarog.brainz.injection

import android.app.Application
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import pl.btwarog.brainz.data.cache.db.BookmarkedArtistsDatabase
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
	fun provideBookmarkedArtistsDatabase(
		application: Application
	): BookmarkedArtistsDatabase {
		return Room.databaseBuilder(
			application.applicationContext,
			BookmarkedArtistsDatabase::class.java,
			"bookmarkedArtists"
		).build()
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