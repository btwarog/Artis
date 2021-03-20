package pl.btwarog.brainz.domain.repository

import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.PaginatedList

interface IArtistsRepository {

	suspend fun getArtists(searchQuery: String, pageSize: Int, nextPageCursor: String?): PaginatedList<ArtistBasicInfo>
}