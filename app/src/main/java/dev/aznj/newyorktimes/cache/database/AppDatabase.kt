package dev.aznj.newyorktimes.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aznj.newyorktimes.cache.MostPopularDao
import dev.aznj.newyorktimes.cache.MostEmailedDao
import dev.aznj.newyorktimes.cache.MostSharedDao
import dev.aznj.newyorktimes.cache.model.MostEmailedEntity
import dev.aznj.newyorktimes.cache.model.MostSharedEntity
import dev.aznj.newyorktimes.cache.model.MostViewedEntity

@Database(entities = [MostViewedEntity::class, MostSharedEntity::class, MostEmailedEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun mostViewedDao(): MostPopularDao
    abstract fun mostSharedDao(): MostSharedDao
    abstract fun mostEmailedDao(): MostEmailedDao

    companion object {
        val DATABASE_NAME: String = "newyorktimes_db"
    }
}