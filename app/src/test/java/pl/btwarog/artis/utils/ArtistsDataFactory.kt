package pl.btwarog.artis.utils

import pl.btwarog.brainz.domain.model.ArtistDetailInfo

object ArtistsDataFactory {

	fun getArtistDetailInfo(bookmarked: Boolean) =
		ArtistDetailInfo(
			getArtistDetailInfoId(),
			"Captain America",
			"The Avenger",
			"Steve Rogers",
			"An extraordinary man",
			"man",
			"person",
			"USA",
			"steveImage",
			"captainImage",
			5.0,
			bookmarked
		)

	fun getArtistDetailInfoId() = "ArtistId"
}