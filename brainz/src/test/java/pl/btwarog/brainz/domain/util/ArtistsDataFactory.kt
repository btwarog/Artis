package pl.btwarog.brainz.domain.util

import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.Artists
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.Node
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.Node.Fragments
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.PageInfo
import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment
import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment.Image
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.PaginatedList

object ArtistsDataFactory {

	fun getMediaWikiImageRemote() = ArtistBasicFragment.MediaWikiImage("MediaWikiImage", "urlToTheImage")

	fun getDiscogBasicRemote() = ArtistBasicFragment.Discogs(
		"Discog", listOf(
			Image(
				"Image",
				"urlToTheImage"
			)
		)
	)

	fun getImageDomain() = "urlToTheImage"

	fun getArtistBasicInfoRemote() = ArtistBasicFragment(
		"ArtistBasicFragment",
		"i1234567890d",
		"Nirvana",
		"Disambiguation",
		listOf(getMediaWikiImageRemote()),
		getDiscogBasicRemote()
	)

	fun getArtistBasicInfoDomain() = ArtistBasicInfo(
		id = "i1234567890d",
		name = "Nirvana",
		disambiguation = "Disambiguation",
		imageUrl = getImageDomain(),
		discogImageUrl = getImageDomain()
	)

	fun getPaginatedListRemote() = Artists(
		"Artists",
		listOf(
			Node(
				"Node",
				Fragments(getArtistBasicInfoRemote())
			)
		),
		PageInfo(
			"PageInfo",
			true,
			"adjh21hasd78kn1231"
		),
		15
	)

	fun getPaginateListDomain() = PaginatedList(
		results = listOf(getArtistBasicInfoDomain()),
		hasNextPage = true,
		nextPageCursor = "adjh21hasd78kn1231"
	)
}