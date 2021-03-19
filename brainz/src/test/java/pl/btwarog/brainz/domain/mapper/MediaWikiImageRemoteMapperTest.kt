package pl.btwarog.brainz.domain.mapper

import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistsDataFactory

class MediaWikiImageRemoteMapperTest {

	private val mediaWikiImageRemoteMapper = MediaWikiImageRemoteMapper()

	private val expectedDomain = ArtistsDataFactory.getMediaWikiImageDomain()

	private val providedRemote = ArtistsDataFactory.getMediaWikiImageRemote()

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