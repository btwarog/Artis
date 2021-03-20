package pl.btwarog.brainz.domain.mapper

import io.mockk.*
import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistBasicInfoDataFactory
import pl.btwarog.brainz.domain.util.CommonDataFactory

class PaginatedArtistsListRemoteMapperTest {

	private val artistBasicInfoMediaWikiImageRemoteMapper: ArtistBasicInfoMediaWikiImageRemoteMapper = mockk()

	private val artistBasicInfoRemoteMapper: ArtistBasicInfoRemoteMapper = mockk()

	private val paginatedArtistsListRemoteMapper = PaginatedArtistsListRemoteMapper(artistBasicInfoRemoteMapper)

	private val expectedDomain = ArtistBasicInfoDataFactory.getPaginateListDomain()

	private val providedRemote = ArtistBasicInfoDataFactory.getPaginatedListRemote()

	init {
		every { artistBasicInfoMediaWikiImageRemoteMapper.mapFromRemote(any()) } returns CommonDataFactory.getImageDomain()
		every { artistBasicInfoRemoteMapper.mapFromRemote(any()) } returns ArtistBasicInfoDataFactory.getArtistBasicInfoDomain()
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