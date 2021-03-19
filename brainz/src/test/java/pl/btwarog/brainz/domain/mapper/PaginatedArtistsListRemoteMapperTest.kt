package pl.btwarog.brainz.domain.mapper

import io.mockk.*
import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistsDataFactory

class PaginatedArtistsListRemoteMapperTest {

	private val mediaWikiImageRemoteMapper: MediaWikiImageRemoteMapper = mockk()

	private val artistBasicInfoRemoteMapper: ArtistBasicInfoRemoteMapper = mockk()

	private val paginatedArtistsListRemoteMapper = PaginatedArtistsListRemoteMapper(artistBasicInfoRemoteMapper)

	private val expectedDomain = ArtistsDataFactory.getPaginateListDomain()

	private val providedRemote = ArtistsDataFactory.getPaginatedListRemote()

	init {
		every { mediaWikiImageRemoteMapper.mapFromRemote(any()) } returns ArtistsDataFactory.getMediaWikiImageDomain()
		every { artistBasicInfoRemoteMapper.mapFromRemote(any()) } returns ArtistsDataFactory.getArtistBasicInfoDomain()
	}

	@Test
	fun `correctly mapped item`() {
		assertThat(paginatedArtistsListRemoteMapper.mapFromRemote(providedRemote)).usingRecursiveComparison()
			.isEqualTo(
				expectedDomain
			)
	}

	@Test
	fun `incorrectly mapped item`() {
		val changedDomain = expectedDomain.copy(hasNextPage = false)
		assertThat(paginatedArtistsListRemoteMapper.mapFromRemote(providedRemote)).usingRecursiveComparison()
			.isNotEqualTo(
				changedDomain
			)
	}
}