package dev.aznj.newyorktimes.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.aznj.newyorktimes.cache.model.MostSharedEntity

@Dao
interface MostSharedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMostShared(mostShared: List<MostSharedEntity>): LongArray

    @Query("SELECT * FROM mostShared")
    suspend fun getAllMostShared(): List<MostSharedEntity>
}