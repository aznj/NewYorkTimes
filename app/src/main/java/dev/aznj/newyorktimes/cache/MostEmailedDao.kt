package dev.aznj.newyorktimes.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aznj.newyorktimes.cache.model.MostEmailedEntity

@Dao
interface MostEmailedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMostEmailed(mostEmailed: List<MostEmailedEntity>): LongArray

    @Query("SELECT * FROM mostEmailed")
    suspend fun getAllMostEmailed(): List<MostEmailedEntity>
}