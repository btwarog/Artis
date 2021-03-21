package pl.btwarog.brainz.domain.usecase

import pl.btwarog.brainz.domain.repository.IArtistsRepository
import javax.inject.Inject

class VerifyArtistBookmarkedUseCase @Inject constructor(private val artistsRepository: IArtistsRepository) {

	suspend fun verifyArtist(
		artistId: String,
	): Boolean {
		return artistsRepository.isArtistBookmarked(artistId)
	}
}