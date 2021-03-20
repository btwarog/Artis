package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.remote.fragment.ArtistDetailFragment.MediaWikiImage
import pl.btwarog.core.domain.mappers.RemoteMapper
import javax.inject.Inject

class ArtistDetailInfoMediaWikiImageRemoteMapper @Inject constructor() : RemoteMapper<MediaWikiImage, String> {

	override fun mapFromRemote(remote: MediaWikiImage): String {
		return remote.url().toString()
	}
}