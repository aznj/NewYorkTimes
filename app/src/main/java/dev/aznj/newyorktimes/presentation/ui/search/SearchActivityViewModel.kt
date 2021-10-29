package dev.aznj.newyorktimes.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aznj.newyorktimes.domain.model.Search
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class SearchActivityViewModel
@Inject
constructor(
    private val repository: SearchRepository,
    private @Named("auth_token") val token: String,
    ) : ViewModel()
{
    var searchViewState: MutableStateFlow<SearchViewState> =
        MutableStateFlow(SearchViewState.EmptyScreen)

    fun processViewAction(action: SearchViewAction) {
        when (action) {
            is SearchViewAction.OnSearchQuery -> {
                getSearchResult(action.query)
            }
        }
    }

    private fun getSearchResult(query: String) {
        repository.getSearchResultsForQuery(token = token, query = query).onEach { dataState ->
            if (dataState.loading) {
                searchViewState.value = SearchViewState.Loading
            }
            dataState.data?.let {
                if (it.isNotEmpty()) {
                    searchViewState.value = SearchViewState.Loaded(it)
                } else {
                    searchViewState.value = SearchViewState.EmptyScreen
                }
            }
            dataState.error?.let { error ->
                searchViewState.value = SearchViewState.ShowError(error)
            }
        }.launchIn(viewModelScope)
    }
}

sealed class SearchViewState {
    object EmptyScreen : SearchViewState()
    data class Loaded(val searchList: List<Search>) : SearchViewState()
    object Loading : SearchViewState()
    data class ResultNotAvailable(val query: String) : SearchViewState()
    data class ShowError(val errorMessage: String) : SearchViewState()
}

sealed class SearchViewAction {
    data class LoadNextPage(val query: String) : SearchViewAction()
    data class OnSearchQuery(val query: String) : SearchViewAction()
    data class OnSearchItemClick(val query: String, val searchResultItem: Search) : SearchViewAction()
}