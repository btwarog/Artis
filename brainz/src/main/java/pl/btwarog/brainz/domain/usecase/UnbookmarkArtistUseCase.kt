package pl.btwarog.brainz.domain.usecase

import pl.btwarog.brainz.domain.repository.IArtistsRepository
import javax.inject.Inject

class UnbookmarkArtistUseCase @Inject constructor(private val artistsRepository: IArtistsRepository) {

	suspend fun unbookmarkArtist(artistId: String): Int {
		return artistsRepository.unbookmarkArtist(artistId)
	}
}