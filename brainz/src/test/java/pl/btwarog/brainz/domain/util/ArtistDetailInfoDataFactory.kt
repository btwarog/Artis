package pl.btwarog.brainz.domain.util

import pl.btwarog.brainz.data.remote.fragment.ArtistDetailFragment
import pl.btwarog.brainz.data.remote.fragment.ArtistDetailFragment.Image
import pl.btwarog.brainz.data.remote.fragment.ArtistDetailFragment.Rating
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.brainz.domain.util.CommonDataFactory.getImageDomain

object ArtistDetailInfoDataFactory {

	fun getMediaWikiImageRemote() = ArtistDetailFragment.MediaWikiImage("MediaWikiImage", "urlToTheImage")

	fun getDiscogBasicRemote() = ArtistDetailFragment.Discogs(
		"Discog",
		"Ada 123",
		"Interesting profile",
		listOf(
			Image(
				"Image",
				getImageDomain()
			)
		)
	)

	fun getArtistDetailInfoRemote() = ArtistDetailFragment(
		"ArtistBasicFragment",
		"i1234567890d",
		"Nirvana",
		"PL",
		"female",
		"person",
		"disambiguation",
		Rating(
			"Rating",
			0.0
		),
		listOf(getMediaWikiImageRemote()),
		getDiscogBasicRemote()
	)

	fun getArtistDetailInfoDomain() = ArtistDetailInfo(
		id = "i1234567890d",
		name = "Nirvana",
		disambiguation = "disambiguation",
		realName = "Ada 123",
		profile = "Interesting profile",
		gender = "female",
		type = "person",
		country = "PL",
		imageUrl = getImageDomain(),
		discogImageUrl = getImageDomain(),
		rating = 0.0
	)
}