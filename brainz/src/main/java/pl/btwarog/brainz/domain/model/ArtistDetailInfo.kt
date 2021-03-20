package pl.btwarog.brainz.domain.model

data class ArtistDetailInfo(
	val id: String,
	val name: String,
	val disambiguation: String,
	val realName: String,
	val profile: String,
	val gender: String,
	val type: String,
	val country: String,
	val imageUrl: String,
	val discogImageUrl: String,
	val rating: Double,
	var bookmarked: Boolean = false
) {

	fun getArtistImageUrl() = if (imageUrl.isEmpty()) discogImageUrl else imageUrl
}