package dev.aznj.newyorktimes.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aznj.newyorktimes.BaseApplication
import dev.aznj.newyorktimes.cache.MostPopularDao
import dev.aznj.newyorktimes.cache.database.AppDatabase
import dev.aznj.newyorktimes.cache.model.MostViewedEntityMapper
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
    fun provideCacheRecipeMapper(): MostViewedEntityMapper{
        return MostViewedEntityMapper()
    }
}