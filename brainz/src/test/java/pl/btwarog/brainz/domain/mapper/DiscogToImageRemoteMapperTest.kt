package pl.btwarog.brainz.domain.mapper

import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistsDataFactory

class DiscogToImageRemoteMapperTest {

	private val discogToImageUrlRemoteMapper = DiscogToImageUrlRemoteMapper()

	private val expectedDomain = ArtistsDataFactory.getImageDomain()

	private val providedRemote = ArtistsDataFactory.getDiscogBasicRemote()

	@Test
	fun `correctly mapped item`() {
		assertThat(discogToImageUrlRemoteMapper.mapFromRemote(providedRemote))
			.isEqualTo(
				expectedDomain
			)
	}

	@Test
	fun `incorrectly mapped item`() {
		val changeDomain = ""
		assertThat(discogToImageUrlRemoteMapper.mapFromRemote(providedRemote))
			.isNotEqualTo(
				changeDomain
			)
	}
}