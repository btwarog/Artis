package pl.btwarog.brainz.domain.model

data class ArtistDetailInfo(
	override val id: String,
	override val name: String,
	override val disambiguation: String,
	val realName: String,
	val profile: String,
	val gender: String,
	val type: String,
	val country: String,
	override val imageUrl: String,
	override val discogImageUrl: String,
	val rating: Double,
	override var bookmarked: Boolean = false
) : IArtistListInfo