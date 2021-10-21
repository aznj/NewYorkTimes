package dev.aznj.newyorktimes

import android.os.Bundle
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
}