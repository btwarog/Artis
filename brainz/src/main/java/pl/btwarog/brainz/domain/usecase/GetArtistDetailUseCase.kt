package pl.btwarog.brainz.domain.usecase

import pl.btwarog.brainz.domain.error.ResultWrapper
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.repository.IArtistsRepository
import javax.inject.Inject

class GetArtistDetailUseCase @Inject constructor(private val artistsRepository: IArtistsRepository) {

	suspend fun getArtistDetail(
		artistId: String
	): ResultWrapper<ArtistDetailInfo> {
		return artistsRepository.getArtistDetail(artistId)
	}
}