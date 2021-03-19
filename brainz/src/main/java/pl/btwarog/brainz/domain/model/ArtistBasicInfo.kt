package pl.btwarog.brainz.domain.model

data class ArtistBasicInfo(
	val id: String,
	val name: String,
	val disambiguation: String,
	val imageUrl: String
)