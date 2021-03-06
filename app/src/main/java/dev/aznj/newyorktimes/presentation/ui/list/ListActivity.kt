package dev.aznj.newyorktimes.presentation.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.aznj.newyorktimes.BaseActivity
import dev.aznj.newyorktimes.R
import dev.aznj.newyorktimes.databinding.ActivityDetailBinding
import dev.aznj.newyorktimes.domain.model.MostPopular
import dev.aznj.newyorktimes.presentation.component.EmptyScreen
import dev.aznj.newyorktimes.presentation.component.ListCard
import dev.aznj.newyorktimes.presentation.component.LoadingProgressBar
import dev.aznj.newyorktimes.util.Constant
import dev.aznj.newyorktimes.util.reFormatDate

@AndroidEntryPoint
class ListActivity : BaseActivity() {

    companion object {
        const val EXTRA_LIST_TYPE = "list_type"

        fun newIntent(context: Context, listType: String): Intent {
            return Intent(context, ListActivity::class.java).apply {
                putExtra(EXTRA_LIST_TYPE, listType)
            }
        }
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: ListActivityViewModel
    private var listType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupView(savedInstanceState)
    }

    override fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(ListActivityViewModel::class.java)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listType = intent.getStringExtra(EXTRA_LIST_TYPE)

        listType?.let {
            viewModel.processEvent(
                MostPopularListEvent.GetMostPopularEvent(it)
            )
        }

        toggleBackButton(true)

        binding.detailComposeView.setContent {
            MostPopularComposable(
                mostPopularViewState = viewModel.mostPopularViewState.collectAsState().value
            )
        }
    }
}

@Composable
private fun MostPopularComposable(
    mostPopularViewState: GetMostPopularViewState
) {
    when (mostPopularViewState) {
        is GetMostPopularViewState.Loaded -> {
            MostPopularList(lists = mostPopularViewState.mostPopularList)
        }
        is GetMostPopularViewState.Loading -> {
            LoadingProgressBar()
        }
        is GetMostPopularViewState.EmptyScreen -> {
            EmptyScreen(stringResource(id = R.string.empty))
        }
        is GetMostPopularViewState.ShowError -> {
            Text(text = mostPopularViewState.errorMessage)
        }
    }
}

@Composable
private fun MostPopularList(lists: List<MostPopular>) {
    LazyColumn {
        itemsIndexed(
            items = lists
        ) { index, item ->
            ListCard(
                title = item.title,
                publishedDate = reFormatDate(
                    Constant.DATE_INPUT_FORMAT,
                    Constant.DATE_OUTPUT_FORMAT,
                    item.publishedDate
                ),
                onClick = {}
            )
        }
    }
}