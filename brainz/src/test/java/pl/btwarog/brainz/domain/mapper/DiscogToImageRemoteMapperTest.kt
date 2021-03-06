package pl.btwarog.brainz.domain.mapper

import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistBasicInfoDataFactory
import pl.btwarog.brainz.domain.util.CommonDataFactory

class DiscogToImageRemoteMapperTest {

	private val discogToImageUrlRemoteMapper = ArtistBasicDiscogToImageUrlRemoteMapper()

	private val expectedDomain = CommonDataFactory.getImageDomain()

	private val providedRemote = ArtistBasicInfoDataFactory.getDiscogBasicRemote()

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