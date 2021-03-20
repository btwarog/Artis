package pl.btwarog.brainz.presentation.paging.factory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import pl.btwarog.brainz.presentation.paging.ArtistListDataSource
import pl.btwarog.brainz.domain.usecase.GetArtistsListUseCase
import javax.inject.Inject

class ArtistListDataFactory @Inject constructor(
	private val getArtistsListUseCase: GetArtistsListUseCase,
	private val pagingConfig: PagingConfig
) {

	fun create(query: String) = Pager(
		config = pagingConfig,
		pagingSourceFactory = { ArtistListDataSource(query, getArtistsListUseCase, pagingConfig) }
	).flow
}