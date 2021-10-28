package dev.aznj.newyorktimes.presentation.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aznj.newyorktimes.domain.model.MostPopular
import dev.aznj.newyorktimes.presentation.ui.MostViewedRepository
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class ListActivityViewModel
@Inject
constructor(
    private val repository: MostViewedRepository,
    private @Named("auth_token") val token: String,
) : ViewModel() {

    val mostPopulars: MutableState<List<MostPopular>> = mutableStateOf(ArrayList())

    val loading = mutableStateOf(false)

    var mostPopularViewState: MutableStateFlow<GetMostPopularViewState> =
        MutableStateFlow(GetMostPopularViewState.Loading)

    fun processEvent(event: MostPopularListEvent) {
        when (event) {
            is MostPopularListEvent.GetMostPopularEvent -> {
                getMostViewed(event.listType)
            }
        }
    }

    private fun getMostViewed(listType: String) {
        repository.getMostViewed(token = token, listType = listType).onEach { dataState ->
            if (dataState.loading) {
                mostPopularViewState.value = GetMostPopularViewState.Loading
            }
            dataState.data?.let {
                if (it.isNotEmpty()) {
                    mostPopularViewState.value = GetMostPopularViewState.Loaded(it)
                } else {
                    mostPopularViewState.value = GetMostPopularViewState.EmptyScreen
                }
            }

            dataState.error?.let { error ->
                mostPopularViewState.value = GetMostPopularViewState.ShowError(error)
            }

            /*loading.value = dataState.loading

            dataState.data?.let { list ->
                mostPopulars.value = list
            }

            dataState.error?.let { error ->

            }*/
        }.launchIn(viewModelScope)
    }
}

sealed class MostPopularListEvent {
    data class GetMostPopularEvent(val listType: String) : MostPopularListEvent()
}

sealed class GetMostPopularViewState {
    object EmptyScreen : GetMostPopularViewState()
    object Loading : GetMostPopularViewState()
    data class Loaded(val mostPopularList: List<MostPopular>) : GetMostPopularViewState()
    data class ShowError(val errorMessage: String) : GetMostPopularViewState()
}