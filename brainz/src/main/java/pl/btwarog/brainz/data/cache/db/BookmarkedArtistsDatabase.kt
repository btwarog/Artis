package pl.btwarog.brainz.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.btwarog.brainz.data.cache.dao.CachedBookmarkedArtistsDao
import pl.btwarog.brainz.data.cache.model.CachedBookmarkedArtistItem

@Database(
    entities = [CachedBookmarkedArtistItem::class],
    version = 1
)
abstract class BookmarkedArtistsDatabase : RoomDatabase() {

	abstract fun cachedBookmarkedArtistsDao(): CachedBookmarkedArtistsDao
}