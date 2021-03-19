package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.remote.BrowseArtistsQuery
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.Artists
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.core.domain.mappers.RemoteMapper
import javax.inject.Inject

internal class PaginatedArtistsListRemoteMapper @Inject constructor(private val basicInfoRemoteMapper: ArtistBasicInfoRemoteMapper) :
	RemoteMapper<BrowseArtistsQuery.Artists, PaginatedList<ArtistBasicInfo>> {

	override fun mapFromRemote(remote: Artists): PaginatedList<ArtistBasicInfo> {
		return PaginatedList(
			results = remote.nodes()?.map { node ->
				basicInfoRemoteMapper.mapFromRemote(node.fragments().artistBasicFragment())
			},
			hasNextPage = remote.pageInfo().hasNextPage(),
			nextPageCursor = remote.pageInfo().endCursor()
		)
	}
}