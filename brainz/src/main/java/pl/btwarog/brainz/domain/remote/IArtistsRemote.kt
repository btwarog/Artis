package pl.btwarog.brainz.domain.remote

import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.model.PaginatedList

interface IArtistsRemote {

	suspend fun getArtists(searchQuery: String, pageSize: Int, nextPageCursor: String?): PaginatedList<ArtistBasicInfo>

	suspend fun getArtistDetail(artistId: String): ArtistDetailInfo
}