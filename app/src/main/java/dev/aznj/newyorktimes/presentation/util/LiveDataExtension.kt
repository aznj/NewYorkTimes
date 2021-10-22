package dev.aznj.newyorktimes.presentation.util

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData

inline fun <reified T> MutableLiveData<T>.setValueOnMainThread(t: T?) {
    Handler(Looper.getMainLooper()).post {
        setValue(t)
    }
}