package pl.btwarog.brainz.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.model.PaginatedList

interface IArtistsRepository {

	suspend fun getArtists(
		searchQuery: String,
		pageSize: Int,
		nextPageCursor: String?
	): ResultWrapper<PaginatedList<ArtistBasicInfo>>

	suspend fun getArtistDetail(
		artistId: String
	): ResultWrapper<ArtistDetailInfo>

	suspend fun bookmarkArtist(artistId: String, artistDetailInfo: ArtistDetailInfo?): Long
	suspend fun unbookmarkArtist(artistId: String): Int
	suspend fun isArtistBookmarked(artistId: String): Boolean
	fun getAllBookmarkedArtist(): Flow<List<ArtistDetailInfo>>
}