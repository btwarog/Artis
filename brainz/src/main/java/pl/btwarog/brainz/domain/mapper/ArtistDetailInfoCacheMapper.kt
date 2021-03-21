package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.cache.model.CachedBookmarkedArtistItem
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.core.domain.mappers.CacheMapper
import javax.inject.Inject

class ArtistDetailInfoCacheMapper @Inject constructor() :
	CacheMapper<CachedBookmarkedArtistItem, ArtistDetailInfo> {

	override fun mapFromCache(cache: CachedBookmarkedArtistItem): ArtistDetailInfo {
		return ArtistDetailInfo(
			id = cache.id,
			name = cache.name,
			disambiguation = cache.disambiguation,
			realName = cache.realName,
			profile = cache.profile,
			gender = cache.gender,
			type = cache.type,
			country = cache.country,
			imageUrl = cache.imageUrl,
			discogImageUrl = cache.discogImageUrl,
			rating = cache.rating,
			bookmarked = true
		)
	}

	override fun mapToCache(domain: ArtistDetailInfo): CachedBookmarkedArtistItem {
		return CachedBookmarkedArtistItem(
			id = domain.id,
			name = domain.name,
			disambiguation = domain.disambiguation,
			realName = domain.realName,
			profile = domain.profile,
			gender = domain.gender,
			type = domain.type,
			country = domain.country,
			imageUrl = domain.imageUrl,
			discogImageUrl = domain.discogImageUrl,
			rating = domain.rating
		)
	}
}