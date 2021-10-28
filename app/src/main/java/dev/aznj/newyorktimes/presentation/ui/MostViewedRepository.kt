package dev.aznj.newyorktimes.presentation.ui

import dev.aznj.newyorktimes.cache.MostPopularDao
import dev.aznj.newyorktimes.cache.model.MostViewedEntityMapper
import dev.aznj.newyorktimes.domain.data.DataState
import dev.aznj.newyorktimes.domain.model.MostPopular
import dev.aznj.newyorktimes.network.ApiService
import dev.aznj.newyorktimes.network.model.MostPopularDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MostViewedRepository(
    private val mostPopularDao: MostPopularDao,
    private val entityMapper: MostViewedEntityMapper,
    private val apiService: ApiService,
    private val mostPopularDtoMapper: MostPopularDtoMapper
) {

    companion object {
        const val MOST_VIEWED = "viewed"
        const val MOST_SHARED = "shared"
    }

    fun getMostViewed(
        token: String,
        listType: String,
    ): Flow<DataState<List<MostPopular>>> = flow {
        try {
            emit(DataState.loading())
            try {
                val mostPopular = getMostPopularFromNetwork(
                    token = token,
                    listType = listType
                )
                mostPopularDao.insertMostVieweds(entityMapper.toEntityList(mostPopular))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // query from db
            val cacheResult = mostPopularDao.getAllMostVieweds()

            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Something went wrong"))
        }
    }

    private suspend fun getMostPopularFromNetwork(
        token: String,
        listType: String,
    ): List<MostPopular> {
        val mostPopularResponse = when (listType) {
            MOST_VIEWED -> {
                apiService.viewed(
                    apiKey = token
                ).mostPopulars
            }
            MOST_SHARED -> {
                apiService.shared(
                    apiKey = token
                ).mostPopulars
            }
            else -> {
                apiService.emailed(
                    apiKey = token
                ).mostPopulars
            }
        }
        return mostPopularDtoMapper.toDomainList(
            mostPopularResponse
        )
    }
}