package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment.MediaWikiImage
import pl.btwarog.core.domain.mappers.RemoteMapper
import javax.inject.Inject

class ArtistBasicInfoMediaWikiImageRemoteMapper @Inject constructor() : RemoteMapper<MediaWikiImage, String> {

	override fun mapFromRemote(remote: MediaWikiImage): String {
		return remote.url().toString()
	}
}