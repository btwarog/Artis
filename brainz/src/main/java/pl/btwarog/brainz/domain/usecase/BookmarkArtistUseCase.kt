package pl.btwarog.brainz.domain.usecase

import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.repository.IArtistsRepository
import javax.inject.Inject

class BookmarkArtistUseCase @Inject constructor(private val artistsRepository: IArtistsRepository) {

	suspend fun bookmarkArtist(
		artistId: String,
		artistDetailInfo: ArtistDetailInfo?
	): Long {
		return artistsRepository.bookmarkArtist(artistId, artistDetailInfo)
	}
}