package dev.aznj.newyorktimes.presentation.ui.search

import androidx.lifecycle.LiveData
import dev.aznj.newyorktimes.domain.model.Search
import dev.aznj.newyorktimes.network.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface SearchViewModel {
    val searchViewState: MutableStateFlow<SearchViewState>

    fun processViewAction(action: SearchViewAction)



    // View Result For specific one-off UI states, like showing dialog, etc
    sealed class SearchViewResult {

    }


}