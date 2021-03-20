package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.utils.orUnknown
import pl.btwarog.core.domain.mappers.RemoteMapper
import javax.inject.Inject

class ArtistBasicInfoRemoteMapper @Inject constructor(
	private val artistBasicInfoMediaWikiImageRemoteMapper: ArtistBasicInfoMediaWikiImageRemoteMapper,
	private val artistBasicDiscogToImageUrlRemoteMapper: ArtistBasicDiscogToImageUrlRemoteMapper
) :
	RemoteMapper<ArtistBasicFragment, ArtistBasicInfo> {

	override fun mapFromRemote(remote: ArtistBasicFragment): ArtistBasicInfo {
		return ArtistBasicInfo(
			id = remote.id(),
			name = remote.name().orUnknown(),
			disambiguation = remote.disambiguation().orUnknown(),
			imageUrl = remote.mediaWikiImages()
				.mapNotNull { item -> artistBasicInfoMediaWikiImageRemoteMapper.mapFromRemote(item) }
				.firstOrNull() ?: "",
			discogImageUrl = remote.discogs()?.let { item ->
				artistBasicDiscogToImageUrlRemoteMapper.mapFromRemote(item)
			} ?: ""
		)
	}
}