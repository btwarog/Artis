package pl.btwarog.brainz.domain.util

import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.Artists
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.Node
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.Node.Fragments
import pl.btwarog.brainz.data.remote.BrowseArtistsQuery.PageInfo
import pl.btwarog.brainz.data.remote.fragment.ArtistBasicFragment
import pl.btwarog.brainz.domain.model.ArtistBasicInfo
import pl.btwarog.brainz.domain.model.PaginatedList

object ArtistsDataFactory {

	fun getMediaWikiImageRemote() = ArtistBasicFragment.MediaWikiImage("MediaWikiImage", "urlToTheImage")

	fun getMediaWikiImageDomain() = "urlToTheImage"

	fun getArtistBasicInfoRemote() = ArtistBasicFragment(
		"ArtistBasicFragment",
		"i1234567890d",
		"Nirvana",
		"Disambiguation",
		listOf(getMediaWikiImageRemote())
	)

	fun getArtistBasicInfoDomain() = ArtistBasicInfo(
		id = "i1234567890d",
		name = "Nirvana",
		disambiguation = "Disambiguation",
		imageUrl = getMediaWikiImageDomain(),
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