package pl.btwarog.brainz.presentation.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.btwarog.brainz.domain.error.GeneralException
import pl.btwarog.brainz.domain.error.NetworkException
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.brainz.domain.usecase.GetArtistsListUseCase

class ArtistListDataSource(
	private val query: String,
	private val getArtistsListUseCase: GetArtistsListUseCase,
	private val pagingConfig: PagingConfig
) :
	PagingSource<String, ArtistBasicInfo>() {

	override fun getRefreshKey(state: PagingState<String, ArtistBasicInfo>): String? =
		state.anchorPosition?.let { position ->
			state.closestPageToPosition(position)?.nextKey
		}

	override suspend fun load(params: LoadParams<String>): LoadResult<String, ArtistBasicInfo> {
		return when (val data = getData(params)) {
			is ResultWrapper.Success<PaginatedList<ArtistBasicInfo>> -> {
				LoadResult.Page(
					data.value.results ?: listOf(),
					params.key,
					if (data.value.hasNextPage) data.value.nextPageCursor else null
				)
			}
			ResultWrapper.NetworkError -> {
				LoadResult.Error(NetworkException())
			}
			ResultWrapper.GeneralError -> {
				LoadResult.Error(GeneralException())
			}
		}
	}

	private suspend fun getData(params: LoadParams<String>) =
		getArtistsListUseCase.getArtists(query, pagingConfig.pageSize, params.key)
}