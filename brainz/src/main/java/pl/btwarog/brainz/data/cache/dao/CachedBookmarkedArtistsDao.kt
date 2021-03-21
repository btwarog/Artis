package pl.btwarog.brainz.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.btwarog.brainz.data.cache.db.BookmarkDatabaseConstants.BOOKMARKED_ARTISTS_TABLE_NAME
import pl.btwarog.brainz.data.cache.model.CachedBookmarkedArtistItem

@Dao
abstract class CachedBookmarkedArtistsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun addBookmarkedArtist(cachedBookmarkedArtistItem: CachedBookmarkedArtistItem): Long

	@Query("SELECT id FROM $BOOKMARKED_ARTISTS_TABLE_NAME WHERE id IN (:ids)")
	abstract suspend fun getAllBookmarkedArtistsWithIds(ids: List<String>): List<String>

	@Query("DELETE FROM $BOOKMARKED_ARTISTS_TABLE_NAME WHERE id = :artistId")
	abstract suspend fun deleteBookmarkedArtist(artistId: String): Int

	@Query("SELECT * FROM $BOOKMARKED_ARTISTS_TABLE_NAME WHERE id = :artistId")
	abstract suspend fun getBookmarkedArtist(artistId: String): CachedBookmarkedArtistItem?

	@Query("SELECT * FROM $BOOKMARKED_ARTISTS_TABLE_NAME")
	abstract fun getAllBookmarkedArtists(): Flow<List<CachedBookmarkedArtistItem>>
}