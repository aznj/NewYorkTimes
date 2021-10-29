package dev.aznj.newyorktimes.presentation.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.aznj.newyorktimes.BaseActivity
import dev.aznj.newyorktimes.R
import dev.aznj.newyorktimes.databinding.ActivitySearchBinding
import dev.aznj.newyorktimes.domain.model.Search
import dev.aznj.newyorktimes.presentation.component.EmptyScreen
import dev.aznj.newyorktimes.presentation.component.ListCard
import dev.aznj.newyorktimes.presentation.component.LoadingProgressBar
import dev.aznj.newyorktimes.presentation.util.DebounceClickListener
import dev.aznj.newyorktimes.util.Constant
import dev.aznj.newyorktimes.util.reFormatDate
import java.util.*

@AndroidEntryPoint
class SearchActivity : BaseActivity() {

    companion object {


        fun newIntent(context: Context): Intent = Intent(context, SearchActivity::class.java)
    }

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView(savedInstanceState)
        setupViewModel()
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarLayout.toolbar)
        super.toggleBackButton(true)
        binding.appBarLayout.productSearchButtonImageView.setOnClickListener(clickListener)
        binding.appBarLayout.searchBarTextInputLayout.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
            }
            false
        }
        toggleKeyboard(true, binding.appBarLayout.searchBarTextInputLayout)
        binding.searchComposeView.setContent {
            SearchResultComposable(
                searchResultViewState = viewModel.searchViewState.collectAsState().value
            )
        }
    }

    private val clickListener = object : DebounceClickListener() {
        override fun onDebouncedClick(view: View) {
            when (view) {
                binding.appBarLayout.productSearchButtonImageView -> {
                    hideKeyboard()
                    performSearch()
                }
            }
        }
    }

    private fun performSearch() {
        val text = binding.appBarLayout.searchBarTextInputLayout.text.toString()
        viewModel.processViewAction(SearchViewAction.OnSearchQuery(text))
    }
}

@Composable
private fun SearchResultComposable(
    searchResultViewState: SearchViewState
) {
    when (searchResultViewState) {
        is SearchViewState.Loaded -> {
            SearchList(lists = searchResultViewState.searchList)
        }
        is SearchViewState.Loading -> {
            LoadingProgressBar()
        }
        is SearchViewState.EmptyScreen -> {
            EmptyScreen(stringResource(id = R.string.start_typing))
        }
        is SearchViewState.ShowError -> {
            Text(text = searchResultViewState.errorMessage)
        }
    }
}

@Composable
private fun SearchList(lists: List<Search>) {
    LazyColumn {
        itemsIndexed(
            items = lists
        ) { index, item ->
            ListCard(
                title = item.abstract,
                publishedDate = reFormatDate(
                    Constant.DATE_INPUT_FORMAT,
                    Constant.DATE_OUTPUT_FORMAT,
                    item.publishedDate.substringBefore('T')
                ),
                onClick = {}
            )
        }
    }
}