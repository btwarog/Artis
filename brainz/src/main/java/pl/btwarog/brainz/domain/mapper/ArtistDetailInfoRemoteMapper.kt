package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.remote.fragment.ArtistDetailFragment
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.utils.orUnknown
import pl.btwarog.core.domain.mappers.RemoteMapper
import javax.inject.Inject

class ArtistDetailInfoRemoteMapper @Inject constructor(
	private val artistDetailInfoMediaWikiImageRemoteMapper: ArtistDetailInfoMediaWikiImageRemoteMapper
) :
	RemoteMapper<ArtistDetailFragment, ArtistDetailInfo> {

	override fun mapFromRemote(remote: ArtistDetailFragment): ArtistDetailInfo {
		return ArtistDetailInfo(
			id = remote.id(),
			name = remote.name().orUnknown(),
			disambiguation = remote.disambiguation().orUnknown(),
			realName = remote.discogs()?.realName().orUnknown(),
			profile = remote.discogs()?.profile().orUnknown(),
			gender = remote.gender().orUnknown(),
			type = remote.type().orUnknown(),
			country = remote.country().orUnknown(),
			imageUrl = remote.mediaWikiImages()
				.mapNotNull { item -> artistDetailInfoMediaWikiImageRemoteMapper.mapFromRemote(item) }
				.firstOrNull() ?: "",
			discogImageUrl = remote.discogs()?.images()?.firstOrNull()?.url()?.toString() ?: "",
			rating = remote.rating()?.value() ?: 0.0
		)
	}
}