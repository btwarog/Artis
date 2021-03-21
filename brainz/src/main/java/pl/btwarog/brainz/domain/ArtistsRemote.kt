package pl.btwarog.brainz.domain

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import pl.btwarog.brainz.data.remote.ArtistDetailQuery
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery
import pl.btwarog.brainz.domain.mapper.ArtistDetailInfoRemoteMapper
import pl.btwarog.brainz.domain.mapper.PaginatedArtistsListRemoteMapper
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.brainz.domain.remote.IArtistsRemote

internal class ArtistsRemote(
	private val apolloClient: ApolloClient,
	private val paginatedArtistsListRemoteMapper: PaginatedArtistsListRemoteMapper,
	private val artistDetailInfoRemoteMapper: ArtistDetailInfoRemoteMapper
) : IArtistsRemote {

	override suspend fun getArtists(
		searchQuery: String,
		pageSize: Int,
		nextPageCursor: String?
	): PaginatedList<ArtistBasicInfo> {
		val data = apolloClient.query(getArtistsBrowseQuery(searchQuery, pageSize, nextPageCursor)).await()
		val result = data.data?.search()?.artists()
		return if (result != null) {
			paginatedArtistsListRemoteMapper.mapFromRemote(result)
		} else {
			throw ApolloException(data.errors?.first()?.message ?: "Unknown Error")
		}
	}

	private fun getArtistsBrowseQuery(searchQuery: String, pageSize: Int, nextPageCursor: String?) =
		BrowseArtistsQuery.builder().searchQuery(searchQuery).pageSize(pageSize).cursor(nextPageCursor).build()

	override suspend fun getArtistDetail(artistId: String): ArtistDetailInfo {
		val data = apolloClient.query(getArtistDetailQuery(artistId)).await()
		if (data.errors?.isNotEmpty() == true) throw ApolloException(data.errors?.first()?.message ?: "")
		val result = data.data?.node()?.fragments()?.artistDetailFragment()
		return if (result != null) {
			artistDetailInfoRemoteMapper.mapFromRemote(result)
		} else {
			throw ApolloException("Empty item")
		}
	}

	private fun getArtistDetailQuery(artistId: String) = ArtistDetailQuery.builder().id(artistId).build()
}