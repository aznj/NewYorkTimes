package dev.aznj.newyorktimes.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aznj.newyorktimes.cache.model.MostEmailedEntity
import dev.aznj.newyorktimes.cache.model.MostSharedEntity
import dev.aznj.newyorktimes.cache.model.MostViewedEntity

@Dao
interface MostPopularDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMostVieweds(mostVieweds: List<MostViewedEntity>): LongArray

    @Query("SELECT * FROM mostViewed")
    suspend fun getAllMostVieweds(): List<MostViewedEntity>
}