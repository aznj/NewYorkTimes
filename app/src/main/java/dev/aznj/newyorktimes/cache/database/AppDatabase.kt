package dev.aznj.newyorktimes.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.aznj.newyorktimes.cache.MostViewedDao
import dev.aznj.newyorktimes.cache.model.MostViewedEntity

@Database(entities = [MostViewedEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun mostViewedDao(): MostViewedDao

    companion object {
        val DATABASE_NAME: String = "newyorktimes_db"
    }
}