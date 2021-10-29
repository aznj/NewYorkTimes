package dev.aznj.newyorktimes.network

import dev.aznj.newyorktimes.network.response.MostPopularResponse
import dev.aznj.newyorktimes.network.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("emailed/7.json")
    suspend fun emailed(
        @Query("api-key") apiKey: String?,
    ): MostPopularResponse

    @GET("viewed/7.json")
    suspend fun viewed(
        @Query("api-key") apiKey: String?,
    ): MostPopularResponse

    @GET("shared/7.json")
    suspend fun shared(
        @Query("api-key") apiKey: String?,
    ): MostPopularResponse
}

interface SearchApiService {

    @GET("articlesearch.json")
    suspend fun search(
        @Query("q") query: String,
        @Query("api-key") apiKey: String?
    ): SearchResponse
}