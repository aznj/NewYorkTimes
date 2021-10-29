package dev.aznj.newyorktimes.presentation.ui.search

import dev.aznj.newyorktimes.cache.SearchDao
import dev.aznj.newyorktimes.cache.model.SearchEntityMapper
import dev.aznj.newyorktimes.domain.data.DataState
import dev.aznj.newyorktimes.domain.model.Search
import dev.aznj.newyorktimes.network.SearchApiService
import dev.aznj.newyorktimes.network.model.SearchDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepository(
    private val searchDao: SearchDao,
    private val searchApiService: SearchApiService,
    private val entityMapper: SearchEntityMapper,
    private val dtoMapper: SearchDtoMapper,
) {
    fun getSearchResultsForQuery(
        token: String,
        query: String,
    ): Flow<DataState<List<Search>>> = flow {
        try {
            emit(DataState.loading())

            try {
                val searchResults = getSearchResultFromNetwork(
                    token = token,
                    query = query
                )
                searchDao.insertSearch((entityMapper.toEntityList(searchResults)))

            } catch (e: Exception) {
                e.printStackTrace()
            }

            val cacheResult = if (query.isBlank()) {
                searchDao.getAllSearch()
            } else {
                searchDao.getAllSearch()
            }

            val list = entityMapper.fromEntityList(cacheResult)
            emit(DataState.success(list))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getSearchResultFromNetwork(
        token: String,
//        page: Int,
        query: String
    ): List<Search> {
        return dtoMapper.toDomainList(
            searchApiService.search(
                apiKey = token,
                query = query,
            ).response.searchResults
        )
    }
}