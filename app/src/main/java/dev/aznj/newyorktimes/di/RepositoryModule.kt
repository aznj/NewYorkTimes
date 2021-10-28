package dev.aznj.newyorktimes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.aznj.newyorktimes.cache.MostEmailedDao
import dev.aznj.newyorktimes.cache.MostPopularDao
import dev.aznj.newyorktimes.cache.MostSharedDao
import dev.aznj.newyorktimes.cache.model.MostEmailedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostSharedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostViewedEntityMapper
import dev.aznj.newyorktimes.network.ApiService
import dev.aznj.newyorktimes.network.model.MostPopularDtoMapper
import dev.aznj.newyorktimes.presentation.ui.MostViewedRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideMostViewedRepository(
        mostPopularDao: MostPopularDao,
        mostSharedDao: MostSharedDao,
        mostEmailedDao: MostEmailedDao,
        mostViewedEntityMapper: MostViewedEntityMapper,
        mostSharedEntityMapper: MostSharedEntityMapper,
        mostEmailedEntityMapper: MostEmailedEntityMapper,
        apiService: ApiService,
        mostPopularDtoMapper: MostPopularDtoMapper
    ): MostViewedRepository {
        return MostViewedRepository(
            mostPopularDao = mostPopularDao,
            mostSharedDao = mostSharedDao,
            mostEmailedDao = mostEmailedDao,
            mostViewedEntityMapper = mostViewedEntityMapper,
            mostSharedEntityMapper = mostSharedEntityMapper,
            mostEmailedEntityMapper = mostEmailedEntityMapper,
            apiService = apiService,
            mostPopularDtoMapper = mostPopularDtoMapper
        )
    }
}