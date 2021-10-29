package dev.aznj.newyorktimes.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aznj.newyorktimes.network.ApiService
import dev.aznj.newyorktimes.network.SearchApiService
import dev.aznj.newyorktimes.network.model.MostPopularDto
import dev.aznj.newyorktimes.network.model.MostPopularDtoMapper
import dev.aznj.newyorktimes.network.model.SearchDtoMapper
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiMapper(): MostPopularDtoMapper {
        return MostPopularDtoMapper()
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchApiMapper(): SearchDtoMapper {
        return SearchDtoMapper()
    }

    @Singleton
    @Provides
    fun provideSearchApiService(): SearchApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/search/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SearchApiService::class.java)
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String{
        return "gLcOdVMoYp8Ds7yfqctlA89J0upmXr7F"
    }
}