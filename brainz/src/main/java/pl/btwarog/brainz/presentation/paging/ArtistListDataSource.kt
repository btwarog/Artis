package pl.btwarog.brainz.presentation.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.btwarog.brainz.domain.error.GeneralException
import pl.btwarog.brainz.domain.error.NetworkException
import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.IArtistListInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.brainz.domain.usecase.GetArtistsListUseCase

class ArtistListDataSource(
	private val query: String,
	private val getArtistsListUseCase: GetArtistsListUseCase,
	private val pagingConfig: PagingConfig
) : PagingSource<String, IArtistListInfo>() {

	override fun getRefreshKey(state: PagingState<String, IArtistListInfo>): String? =
		null

	override suspend fun load(params: LoadParams<String>): LoadResult<String, IArtistListInfo> {
		return when (val data = getData(params)) {
			is ResultWrapper.Success<PaginatedList<ArtistBasicInfo>> -> {
				val list = data.value.results ?: listOf()
				LoadResult.Page(
					list as List<IArtistListInfo>,
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