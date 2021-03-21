package pl.btwarog.brainz.domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.repository.IArtistsRepository
import javax.inject.Inject

class GetBookmarkedArtistsListUseCase @Inject constructor(private val artistsRepository: IArtistsRepository) {

	fun getBookmarkedArtists(): Flow<List<ArtistDetailInfo>> {
		return artistsRepository.getAllBookmarkedArtist()
	}
}