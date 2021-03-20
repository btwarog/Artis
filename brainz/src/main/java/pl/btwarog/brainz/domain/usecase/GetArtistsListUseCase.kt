package pl.btwarog.brainz.domain.usecase

import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.PaginatedList
import pl.btwarog.brainz.domain.repository.IArtistsRepository
import javax.inject.Inject

class GetArtistsListUseCase @Inject constructor(private val artistsRepository: IArtistsRepository) {

	suspend fun getArtists(
		query: String,
		pageSize: Int,
		cursor: String?
	): ResultWrapper<PaginatedList<ArtistBasicInfo>> {
		return artistsRepository.getArtists(query, pageSize, cursor)
	}
}