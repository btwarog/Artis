package pl.btwarog.brainz.domain.cache

import kotlinx.coroutines.flow.Flow
import pl.btwarog.brainz.domain.model.ArtistDetailInfo

interface IArtistsCache {

	suspend fun addBookmarkedArtist(artistDetailInfo: ArtistDetailInfo): Long

	suspend fun getAllBookmarkedArtistsWithIds(ids: List<String>): List<String>

	suspend fun deleteBookmarkedArtist(artistId: String): Int

	suspend fun getBookmarkedArtist(artistId: String): ArtistDetailInfo?

	fun getAllBookmarkedArtists(): Flow<List<ArtistDetailInfo>>
}