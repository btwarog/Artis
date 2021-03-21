package pl.btwarog.brainz.domain

import kotlinx.coroutines.flow.Flow
import pl.btwarog.brainz.domain.cache.IArtistsCache
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.error.safeApiCall
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.brainz.domain.remote.IArtistsRemote
import pl.btwarog.brainz.domain.repository.IArtistsRepository

class ArtistsRepository(private val iArtistsRemote: IArtistsRemote, private val iArtistsCache: IArtistsCache) :
	IArtistsRepository {

	override suspend fun getArtists(
		searchQuery: String,
		pageSize: Int,
		nextPageCursor: String?
	): ResultWrapper<PaginatedList<ArtistBasicInfo>> {
		return safeApiCall {
			val remoteData = iArtistsRemote.getArtists(searchQuery, pageSize, nextPageCursor)
			val ids = remoteData.results?.map { item -> item.id } ?: listOf()
			if (ids.isNotEmpty()) {
				val bookmarkedArtistsIds = iArtistsCache.getAllBookmarkedArtistsWithIds(ids)
				val test =
					remoteData.results?.map { item -> item.copy(bookmarked = bookmarkedArtistsIds.contains(item.id)) }
						?: listOf()
				remoteData.copy(
					results = test
				)
			} else {
				remoteData
			}
		}
	}

	override suspend fun getArtistDetail(
		artistId: String
	): ResultWrapper<ArtistDetailInfo> {
		val data = iArtistsCache.getBookmarkedArtist(artistId)
		return if (data != null) {
			ResultWrapper.Success(data)
		} else {
			safeApiCall { iArtistsRemote.getArtistDetail(artistId) }
		}
	}

	override suspend fun bookmarkArtist(
		artistId: String,
		artistDetailInfo: ArtistDetailInfo?
	): Long {
		return if (artistDetailInfo != null) {
			iArtistsCache.addBookmarkedArtist(artistDetailInfo)
		} else {
			when (val data = safeApiCall { iArtistsRemote.getArtistDetail(artistId) }) {
				is ResultWrapper.Success -> {
					iArtistsCache.addBookmarkedArtist(data.value)
				}
				else -> -1L
			}
		}
	}

	override suspend fun unbookmarkArtist(artistId: String): Int {
		return iArtistsCache.deleteBookmarkedArtist(artistId)
	}

	override suspend fun isArtistBookmarked(artistId: String): Boolean {
		return iArtistsCache.getBookmarkedArtist(artistId) != null
	}

	override fun getAllBookmarkedArtist(): Flow<List<ArtistDetailInfo>> {
		return iArtistsCache.getAllBookmarkedArtists()
	}
}