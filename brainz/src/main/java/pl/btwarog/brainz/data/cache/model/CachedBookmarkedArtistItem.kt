package pl.btwarog.brainz.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.btwarog.brainz.data.cache.db.BookmarkDatabaseConstants

@Entity(tableName = BookmarkDatabaseConstants.BOOKMARKED_ARTISTS_TABLE_NAME)
data class CachedBookmarkedArtistItem(
    @PrimaryKey(autoGenerate = false)
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
)