package dev.aznj.newyorktimes.presentation.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aznj.newyorktimes.presentation.util.setValueOnMainThread
import javax.inject.Inject

@HiltViewModel
class ListFragmentViewModel @Inject constructor() : ViewModel() {

    private val _navigationResultLiveData = MediatorLiveData<ListFragmentNavigationResult>()
    var navigationResultLiveData: LiveData<ListFragmentNavigationResult>

    init {
        navigationResultLiveData = Transformations.map(_navigationResultLiveData) { it }
    }

    fun processNavigationAction(action: ListFragmentNavigationAction) {
        when (action) {
            is ListFragmentNavigationAction.ShowMostPopular -> {
                _navigationResultLiveData.setValueOnMainThread(
                    ListFragmentNavigationResult.NavigateToMostPopular(action.listType)
                )
            }
        }
    }
}

sealed class ListFragmentNavigationAction {
    data class ShowMostPopular(val listType: String): ListFragmentNavigationAction()
}

sealed class ListFragmentNavigationResult {
    data class NavigateToMostPopular(val listType: String): ListFragmentNavigationResult()
}