package pl.btwarog.brainz.domain.mapper

import io.mockk.*
import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistBasicInfoDataFactory
import pl.btwarog.brainz.domain.util.CommonDataFactory

class ArtistBasicInfoRemoteMapperTest {

	private val artistBasicInfoMediaWikiImageRemoteMapper: ArtistBasicInfoMediaWikiImageRemoteMapper = mockk()

	private val artistBasicDiscogToImageUrlRemoteMapper: ArtistBasicDiscogToImageUrlRemoteMapper = mockk()

	private val artistBasicInfoRemoteMapper =
		ArtistBasicInfoRemoteMapper(artistBasicInfoMediaWikiImageRemoteMapper, artistBasicDiscogToImageUrlRemoteMapper)

	private val expectedDomain = ArtistBasicInfoDataFactory.getArtistBasicInfoDomain()

	private val providedRemote = ArtistBasicInfoDataFactory.getArtistBasicInfoRemote()

	init {
		every { artistBasicInfoMediaWikiImageRemoteMapper.mapFromRemote(any()) } returns CommonDataFactory.getImageDomain()
		every { artistBasicDiscogToImageUrlRemoteMapper.mapFromRemote(any()) } returns CommonDataFactory.getImageDomain()
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