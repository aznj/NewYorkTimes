package dev.aznj.newyorktimes.presentation.util

import android.util.SparseLongArray
import android.view.View

abstract class DebounceClickListener(val minimumClicksIntervalMillis: Long = 800) : View.OnClickListener {

    private val clickedViewsIdsMap: SparseLongArray by lazy { SparseLongArray() }

    abstract fun onDebouncedClick(view: View)

    override fun onClick(clickedView: View?) {
        val keyNotFoundResultValue: Long = -1
        // Handling multiple clicks for the same View
        clickedView?.let {
            val previousClickTimestampForViewId = clickedViewsIdsMap.get(it.id, keyNotFoundResultValue)
            val currentTimestamp: Long = System.currentTimeMillis()

            val isFirstTimeToClickView = previousClickTimestampForViewId == keyNotFoundResultValue
            val clicksDifferenceBiggerThanInterval =
                Math.abs(currentTimestamp - previousClickTimestampForViewId) > minimumClicksIntervalMillis
            // if the View is clicked for the first time, or it has been clicked for longer than minimumClickIntervalMillis
            clickedViewsIdsMap.put(it.id, currentTimestamp)
            if (isFirstTimeToClickView || clicksDifferenceBiggerThanInterval) onDebouncedClick(clickedView)
        }
    }
}