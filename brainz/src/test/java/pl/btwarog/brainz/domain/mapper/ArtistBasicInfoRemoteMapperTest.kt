package pl.btwarog.brainz.domain.mapper

import io.mockk.*
import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistsDataFactory

class ArtistBasicInfoRemoteMapperTest {

	private val mediaWikiImageRemoteMapper: MediaWikiImageRemoteMapper = mockk()

	private val artistBasicInfoRemoteMapper = ArtistBasicInfoRemoteMapper(mediaWikiImageRemoteMapper)

	private val expectedDomain = ArtistsDataFactory.getArtistBasicInfoDomain()

	private val providedRemote = ArtistsDataFactory.getArtistBasicInfoRemote()

	init {
		every { mediaWikiImageRemoteMapper.mapFromRemote(any()) } returns ArtistsDataFactory.getMediaWikiImageDomain()
	}

	@Test
	fun `correctly mapped item`() {
		assertThat(artistBasicInfoRemoteMapper.mapFromRemote(providedRemote)).usingRecursiveComparison()
			.isEqualTo(
				expectedDomain
			)
	}

	@Test
	fun `incorrectly mapped item`() {
		val changedDomain = expectedDomain.copy(id = "Test")
		assertThat(artistBasicInfoRemoteMapper.mapFromRemote(providedRemote)).usingRecursiveComparison()
			.isNotEqualTo(
				changedDomain
			)
	}
}