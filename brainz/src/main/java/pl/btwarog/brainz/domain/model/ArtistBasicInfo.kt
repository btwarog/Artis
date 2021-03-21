package pl.btwarog.brainz.domain.model

data class ArtistBasicInfo(
	override val id: String,
	override val name: String,
	override val disambiguation: String,
	override val imageUrl: String,
	override val discogImageUrl: String,
	override var bookmarked: Boolean = false
) : IArtistListInfo