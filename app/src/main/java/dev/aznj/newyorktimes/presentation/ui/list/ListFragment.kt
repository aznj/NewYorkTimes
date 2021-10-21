package dev.aznj.newyorktimes.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.aznj.newyorktimes.BaseFragment
import dev.aznj.newyorktimes.R
import dev.aznj.newyorktimes.compose.Grey
import dev.aznj.newyorktimes.compose.MyApplicationTheme
import dev.aznj.newyorktimes.databinding.ActivityMainBinding
import dev.aznj.newyorktimes.presentation.component.CardItem

class ListFragment : BaseFragment<ActivityMainBinding>() {

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    ListComposable(
                        onSearchClick = {

                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ListComposable(
        onSearchClick: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState(0))
        ) {
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                stringResource(R.string.search),
                style = MaterialTheme.typography.body1.copy(color = Grey),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            CardItem(
                stringResource(id = R.string.search_articles),
                RoundedCornerShape(8.dp),
                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                onSearchClick
            )

            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Text(
                stringResource(R.string.popular),
                style = MaterialTheme.typography.body1.copy(color = Grey),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            CardItem(
                stringResource(id = R.string.most_viewed),
                RoundedCornerShape(8.dp),
                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                onSearchClick
            )
            CardItem(
                stringResource(id = R.string.most_shared),
                RoundedCornerShape(8.dp),
                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                onSearchClick
            )
            CardItem(
                stringResource(id = R.string.most_emailed),
                RoundedCornerShape(8.dp),
                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                onSearchClick
            )
        }
    }
}