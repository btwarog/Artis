package pl.btwarog.brainz.domain

import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.brainz.domain.remote.IArtistsRemote
import pl.btwarog.brainz.domain.repository.IArtistsRepository

class ArtistsRepository(private val iArtistsRemote: IArtistsRemote) : IArtistsRepository {

	override suspend fun getArtists(
		searchQuery: String,
		pageSize: Int,
		nextPageCursor: String?
	): PaginatedList<ArtistBasicInfo> {
		return iArtistsRemote.getArtists(searchQuery, pageSize, nextPageCursor)
	}
}