package dev.aznj.newyorktimes.presentation.ui

import dev.aznj.newyorktimes.cache.MostEmailedDao
import dev.aznj.newyorktimes.cache.MostPopularDao
import dev.aznj.newyorktimes.cache.MostSharedDao
import dev.aznj.newyorktimes.cache.model.MostEmailedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostSharedEntityMapper
import dev.aznj.newyorktimes.cache.model.MostViewedEntityMapper
import dev.aznj.newyorktimes.domain.data.DataState
import dev.aznj.newyorktimes.domain.model.MostPopular
import dev.aznj.newyorktimes.network.ApiService
import dev.aznj.newyorktimes.network.model.MostPopularDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MostViewedRepository(
    private val mostPopularDao: MostPopularDao,
    private val mostSharedDao: MostSharedDao,
    private val mostEmailedDao: MostEmailedDao,
    private val mostViewedEntityMapper: MostViewedEntityMapper,
    private val mostSharedEntityMapper: MostSharedEntityMapper,
    private val mostEmailedEntityMapper: MostEmailedEntityMapper,
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
                when (listType) {
                    MOST_VIEWED -> {
                        mostPopularDao.insertMostVieweds(mostViewedEntityMapper.toEntityList(mostPopular))
                    }
                    MOST_SHARED -> {
                        mostSharedDao.insertMostShared(mostSharedEntityMapper.toEntityList(mostPopular))
                    }
                    else -> {
                        mostEmailedDao.insertMostEmailed(mostEmailedEntityMapper.toEntityList(mostPopular))
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            emit(DataState.success(queryFromDatabase(listType)))
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Something went wrong"))
        }
    }

    // query from db
    private suspend fun queryFromDatabase(listType: String): List<MostPopular> {
        return when (listType) {
            MOST_VIEWED -> {
                val cacheResult = mostPopularDao.getAllMostVieweds()
                mostViewedEntityMapper.fromEntityList(cacheResult)
            }
            MOST_SHARED -> {
                val cacheResult = mostSharedDao.getAllMostShared()
                mostSharedEntityMapper.fromEntityList(cacheResult)
            }
            else -> {
                val cacheResult = mostEmailedDao.getAllMostEmailed()
                mostEmailedEntityMapper.fromEntityList(cacheResult)
            }
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