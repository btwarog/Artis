package pl.btwarog.brainz.domain.model

interface IArtistListInfo {

	val id: String
	val name: String
	val disambiguation: String
	val imageUrl: String
	val discogImageUrl: String
	var bookmarked: Boolean
	fun getArtistImageUrl() = if (imageUrl.isEmpty()) {
		discogImageUrl
	} else {
		imageUrl
	}
}