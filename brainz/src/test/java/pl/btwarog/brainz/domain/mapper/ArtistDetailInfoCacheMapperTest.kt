package pl.btwarog.brainz.domain.mapper

import org.assertj.core.api.Assertions.*
import org.junit.*
import pl.btwarog.brainz.domain.util.ArtistDetailInfoDataFactory

class ArtistDetailInfoCacheMapperTest {

	private val artistDetailInfoCacheMapper = ArtistDetailInfoCacheMapper()

	private val expectedDomain = ArtistDetailInfoDataFactory.getArtistDetailInfoDomain(bookmarked = true)

	private val expectedCache = ArtistDetailInfoDataFactory.getCachedBookmarkedArtistItem()

	@Test
	fun `correctly mapped item from cache`() {
		assertThat(artistDetailInfoCacheMapper.mapFromCache(expectedCache)).usingRecursiveComparison()
			.isEqualTo(
				expectedDomain
			)
	}

	@Test
	fun `correctly mapped item to cache`() {
		assertThat(artistDetailInfoCacheMapper.mapToCache(expectedDomain)).usingRecursiveComparison()
			.isEqualTo(
				expectedCache
			)
	}

	@Test
	fun `incorrectly mapped item from cache`() {
		val changedDomain = expectedDomain.copy(bookmarked = false)
		assertThat(artistDetailInfoCacheMapper.mapFromCache(expectedCache)).usingRecursiveComparison()
			.isNotEqualTo(
				changedDomain
			)
	}

	@Test
	fun `incorrectly mapped item to cache`() {
		val changedCache = expectedCache.copy(id = "wrongId")
		assertThat(artistDetailInfoCacheMapper.mapFromCache(expectedCache)).usingRecursiveComparison()
			.isNotEqualTo(
				changedCache
			)
	}
}