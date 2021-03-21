package pl.btwarog.brainz.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.btwarog.brainz.data.cache.db.BookmarkedArtistsDatabase
import pl.btwarog.brainz.domain.cache.IArtistsCache
import pl.btwarog.brainz.domain.mapper.ArtistDetailInfoCacheMapper
import pl.btwarog.brainz.domain.model.ArtistDetailInfo

class ArtistsCache(
	private val bookmarkedArtistsDatabase: BookmarkedArtistsDatabase,
	private val artistsDetailInfoCacheMapper: ArtistDetailInfoCacheMapper
) : IArtistsCache {

	override suspend fun addBookmarkedArtist(artistDetailInfo: ArtistDetailInfo): Long {
		return bookmarkedArtistsDatabase
			.cachedBookmarkedArtistsDao()
			.addBookmarkedArtist(artistsDetailInfoCacheMapper.mapToCache(artistDetailInfo))
	}

	override suspend fun getAllBookmarkedArtistsWithIds(ids: List<String>): List<String> {
		return bookmarkedArtistsDatabase
			.cachedBookmarkedArtistsDao()
			.getAllBookmarkedArtistsWithIds(ids)
	}

	override suspend fun deleteBookmarkedArtist(artistId: String): Int {
		return bookmarkedArtistsDatabase
			.cachedBookmarkedArtistsDao()
			.deleteBookmarkedArtist(artistId)
	}

	override suspend fun getBookmarkedArtist(artistId: String): ArtistDetailInfo? {
		return bookmarkedArtistsDatabase
			.cachedBookmarkedArtistsDao()
			.getBookmarkedArtist(artistId)?.let { item ->
				artistsDetailInfoCacheMapper.mapFromCache(item)
			}
	}

	override fun getAllBookmarkedArtists(): Flow<List<ArtistDetailInfo>> {
		return bookmarkedArtistsDatabase
			.cachedBookmarkedArtistsDao()
			.getAllBookmarkedArtists()
			.map { list ->
				list.map { item -> artistsDetailInfoCacheMapper.mapFromCache(item) }
			}
	}
}