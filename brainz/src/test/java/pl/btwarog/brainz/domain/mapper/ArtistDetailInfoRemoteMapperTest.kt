package pl.btwarog.brainz.domain.mapper

import io.mockk.*
import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistDetailInfoDataFactory
import pl.btwarog.brainz.domain.util.CommonDataFactory

class ArtistDetailInfoRemoteMapperTest {

	private val artistDetailInfoMediaWikiImageRemoteMapper: ArtistDetailInfoMediaWikiImageRemoteMapper = mockk()

	private val artistDetailInfoRemoteMapper =
		ArtistDetailInfoRemoteMapper(artistDetailInfoMediaWikiImageRemoteMapper)

	private val expectedDomain = ArtistDetailInfoDataFactory.getArtistDetailInfoDomain()

	private val providedRemote = ArtistDetailInfoDataFactory.getArtistDetailInfoRemote()

	init {
		every { artistDetailInfoMediaWikiImageRemoteMapper.mapFromRemote(any()) } returns CommonDataFactory.getImageDomain()
	}

	@Test
	fun `correctly mapped item`() {
		assertThat(artistDetailInfoRemoteMapper.mapFromRemote(providedRemote)).usingRecursiveComparison()
			.isEqualTo(
				expectedDomain
			)
	}

	@Test
	fun `incorrectly mapped item`() {
		val changedDomain = expectedDomain.copy(id = "Test")
		assertThat(artistDetailInfoRemoteMapper.mapFromRemote(providedRemote)).usingRecursiveComparison()
			.isNotEqualTo(
				changedDomain
			)
	}
}