package dev.aznj.newyorktimes

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setupViewModel()
    abstract fun setupView(savedInstanceState: Bundle?)

    /**
     * Show title & hide logo on Action Bar
     */
    open fun setActionBarTitle(title: String) {
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = title
    }

    fun toggleBackButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    fun toggleKeyboard(show: Boolean, editText: EditText) {
        val inputMethodManager = getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        when (show) {
            true -> {
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            }
            false -> {
                inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
            }
        }
    }

    fun hideKeyboard() {
        currentFocus?.let {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}