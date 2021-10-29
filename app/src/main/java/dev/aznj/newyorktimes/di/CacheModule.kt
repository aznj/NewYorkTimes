package dev.aznj.newyorktimes.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aznj.newyorktimes.BaseApplication
import dev.aznj.newyorktimes.cache.MostPopularDao
import dev.aznj.newyorktimes.cache.database.AppDatabase
import dev.aznj.newyorktimes.cache.MostEmailedDao
import dev.aznj.newyorktimes.cache.MostSharedDao
import dev.aznj.newyorktimes.cache.SearchDao
import dev.aznj.newyorktimes.cache.model.MostEmailedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostSharedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostViewedEntityMapper
import dev.aznj.newyorktimes.cache.model.SearchEntityMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMostViewedDao(db: AppDatabase): MostPopularDao{
        return db.mostViewedDao()
    }

    @Singleton
    @Provides
    fun provideMostSharedDao(db: AppDatabase): MostSharedDao{
        return db.mostSharedDao()
    }

    @Singleton
    @Provides
    fun provideMostEmailedDao(db: AppDatabase): MostEmailedDao {
        return db.mostEmailedDao()
    }

    @Singleton
    @Provides
    fun provideSearchDao(db: AppDatabase): SearchDao {
        return db.searchDao()
    }

    @Singleton
    @Provides
    fun provideCacheMostViewedMapper(): MostViewedEntityMapper{
        return MostViewedEntityMapper()
    }

    @Singleton
    @Provides
    fun provideCacheMostSharedMapper(): MostSharedEntityMapper{
        return MostSharedEntityMapper()
    }

    @Singleton
    @Provides
    fun provideCacheMostEmailedMapper(): MostEmailedEntityMapper{
        return MostEmailedEntityMapper()
    }

    @Singleton
    @Provides
    fun provideCacheSearchMapper(): SearchEntityMapper{
        return SearchEntityMapper()
    }
}