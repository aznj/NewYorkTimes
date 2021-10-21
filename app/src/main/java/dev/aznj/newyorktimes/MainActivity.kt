package dev.aznj.newyorktimes

import android.os.Bundle
import dev.aznj.newyorktimes.databinding.ActivityMainBinding
import dev.aznj.newyorktimes.presentation.ui.list.ListFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupView(savedInstanceState)
    }

    override fun setupViewModel() {}

    override fun setupView(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBarTitle(getString(R.string.app_name))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance()).commitNow()
        }
    }
}