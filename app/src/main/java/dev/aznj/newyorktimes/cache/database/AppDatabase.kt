package dev.aznj.newyorktimes.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aznj.newyorktimes.cache.MostPopularDao
import dev.aznj.newyorktimes.cache.model.MostViewedEntity

@Database(entities = [MostViewedEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun mostViewedDao(): MostPopularDao

    companion object {
        val DATABASE_NAME: String = "newyorktimes_db"
    }
}