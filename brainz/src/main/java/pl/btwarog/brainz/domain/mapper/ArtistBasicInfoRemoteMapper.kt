package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.core.domain.mappers.RemoteMapper
import javax.inject.Inject

internal class ArtistBasicInfoRemoteMapper @Inject constructor(private val mediaWikiImageRemoteMapper: MediaWikiImageRemoteMapper) :
	RemoteMapper<ArtistBasicFragment, ArtistBasicInfo> {

	override fun mapFromRemote(remote: ArtistBasicFragment): ArtistBasicInfo {
		return ArtistBasicInfo(
			id = remote.id(),
			name = remote.name() ?: "",
			disambiguation = remote.disambiguation() ?: "",
			imageUrl = remote.mediaWikiImages()
				.mapNotNull { item -> mediaWikiImageRemoteMapper.mapFromRemote(item) }
				.firstOrNull() ?: ""
		)
	}
}