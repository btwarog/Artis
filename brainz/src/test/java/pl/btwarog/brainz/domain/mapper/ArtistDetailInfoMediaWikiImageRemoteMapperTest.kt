package pl.btwarog.brainz.domain.mapper

import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistDetailInfoDataFactory
import pl.btwarog.brainz.domain.util.CommonDataFactory

class ArtistDetailInfoMediaWikiImageRemoteMapperTest {

	private val mediaWikiImageRemoteMapper = ArtistDetailInfoMediaWikiImageRemoteMapper()

	private val expectedDomain = CommonDataFactory.getImageDomain()

	private val providedRemote = ArtistDetailInfoDataFactory.getMediaWikiImageRemote()

	@Test
	fun `correctly mapped item`() {
		assertThat(mediaWikiImageRemoteMapper.mapFromRemote(providedRemote))
			.isEqualTo(
				expectedDomain
			)
	}

	@Test
	fun `incorrectly mapped item`() {
		val changeDomain = ""
		assertThat(mediaWikiImageRemoteMapper.mapFromRemote(providedRemote))
			.isNotEqualTo(
				changeDomain
			)
	}
}