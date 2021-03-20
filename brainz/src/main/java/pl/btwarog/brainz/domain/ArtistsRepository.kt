package pl.btwarog.brainz.domain

import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.error.safeApiCall
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.brainz.domain.remote.IArtistsRemote
import pl.btwarog.brainz.domain.repository.IArtistsRepository

class ArtistsRepository(private val iArtistsRemote: IArtistsRemote) : IArtistsRepository {

	override suspend fun getArtists(
		searchQuery: String,
		pageSize: Int,
		nextPageCursor: String?
	): ResultWrapper<PaginatedList<ArtistBasicInfo>> {
		return safeApiCall { iArtistsRemote.getArtists(searchQuery, pageSize, nextPageCursor) }
	}

	override suspend fun getArtistDetail(
		artistId: String
	): ResultWrapper<ArtistDetailInfo> {
		return safeApiCall { iArtistsRemote.getArtistDetail(artistId) }
	}
}