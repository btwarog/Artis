package pl.btwarog.brainz.domain.mapper

import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment.Discogs
import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment.MediaWikiImage
import pl.btwarog.core.domain.mappers.RemoteMapper
import javax.inject.Inject

class DiscogToImageUrlRemoteMapper @Inject constructor() : RemoteMapper<Discogs, String> {

	override fun mapFromRemote(remote: Discogs): String {
		return remote.images().firstOrNull()?.url()?.toString() ?: ""
	}
}