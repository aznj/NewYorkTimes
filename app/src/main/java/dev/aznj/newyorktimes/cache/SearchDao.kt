package dev.aznj.newyorktimes.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aznj.newyorktimes.cache.model.SearchEntity

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSearch(search: List<SearchEntity>): LongArray

    @Query("SELECT * FROM search")
    suspend fun getAllSearch(): List<SearchEntity>
}