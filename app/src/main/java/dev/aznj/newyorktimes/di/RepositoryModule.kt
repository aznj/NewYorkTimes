package dev.aznj.newyorktimes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.aznj.newyorktimes.cache.MostViewedDao
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
        mostViewedDao: MostViewedDao,
        mostViewedEntityMapper: MostViewedEntityMapper,
        apiService: ApiService,
        mostPopularDtoMapper: MostPopularDtoMapper
    ): MostViewedRepository {
        return MostViewedRepository(
            mostViewedDao = mostViewedDao,
            entityMapper = mostViewedEntityMapper,
            apiService = apiService,
            mostPopularDtoMapper = mostPopularDtoMapper
        )
    }
}