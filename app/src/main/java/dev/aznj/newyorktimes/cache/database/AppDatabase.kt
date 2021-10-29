package dev.aznj.newyorktimes.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aznj.newyorktimes.cache.MostViewedDao
import dev.aznj.newyorktimes.cache.MostEmailedDao
import dev.aznj.newyorktimes.cache.MostSharedDao
import dev.aznj.newyorktimes.cache.SearchDao
import dev.aznj.newyorktimes.cache.model.MostEmailedEntity
import dev.aznj.newyorktimes.cache.model.MostSharedEntity
import dev.aznj.newyorktimes.cache.model.MostViewedEntity
import dev.aznj.newyorktimes.cache.model.SearchEntity

@Database(
    entities = [MostViewedEntity::class, MostSharedEntity::class, MostEmailedEntity::class, SearchEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mostViewedDao(): MostViewedDao
    abstract fun mostSharedDao(): MostSharedDao
    abstract fun mostEmailedDao(): MostEmailedDao
    abstract fun searchDao(): SearchDao

    companion object {
        val DATABASE_NAME: String = "newyorktimes_db"
    }
}