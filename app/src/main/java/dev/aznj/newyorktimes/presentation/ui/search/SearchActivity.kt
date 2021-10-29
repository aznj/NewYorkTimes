package dev.aznj.newyorktimes.presentation.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.aznj.newyorktimes.BaseActivity
import dev.aznj.newyorktimes.databinding.ActivitySearchBinding
import dev.aznj.newyorktimes.domain.model.Search
import dev.aznj.newyorktimes.presentation.util.DebounceClickListener

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
        /*binding.appBarLayout.searchBarTextInputLayout.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
            }
            false
        }*/
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
    }
}

@Composable
private fun SearchList(lists: List<Search>) {
    LazyColumn {
        itemsIndexed(
            items = lists
        ) { index, item ->
            SearchCard(search = item, onClick = {})
        }
    }
}

@Composable
private fun SearchCard(
    search: Search,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 6.dp,
                end = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.padding(
                start = 10.dp, end = 10.dp
            )
        ) {
            Text(
                text = search.abstract,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = search.publishedDate,
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium,
            )
        }

    }
}