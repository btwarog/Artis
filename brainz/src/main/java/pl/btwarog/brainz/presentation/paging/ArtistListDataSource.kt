package pl.btwarog.brainz.presentation.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.exception.ApolloException
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.usecase.GetArtistsListUseCase

class ArtistListDataSource(
	private val query: String,
	private val getArtistsListUseCase: GetArtistsListUseCase,
	private val pagingConfig: PagingConfig
) :
	PagingSource<String, ArtistBasicInfo>() {

	override fun getRefreshKey(state: PagingState<String, ArtistBasicInfo>): String? =
		state.anchorPosition?.let { position ->
			state.closestPageToPosition(position)?.nextKey ?: ""
		}

	override suspend fun load(params: LoadParams<String>): LoadResult<String, ArtistBasicInfo> {
		return try {
			val data = getArtistsListUseCase.getArtists(query, pagingConfig.pageSize, params.key)
			LoadResult.Page(
				data.results ?: listOf(),
				params.key,
				data.nextPageCursor
			)
		} catch (apolloException: ApolloException) {
			LoadResult.Error(apolloException)
		}
	}
}