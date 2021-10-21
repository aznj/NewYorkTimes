package dev.aznj.newyorktimes.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.aznj.newyorktimes.R
import dev.aznj.newyorktimes.compose.Grey
import dev.aznj.newyorktimes.compose.LightGrey


@ExperimentalMaterialApi
@Composable
fun CardItem(
    stringResource: String,
    shape: RoundedCornerShape,
    modifier: Modifier,
    onCardClick: () -> Unit
) {
    Card(
        onClick = { onCardClick() },
        shape = shape,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier,
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource,
                style = MaterialTheme.typography.body2.copy(color = Grey),
                modifier = Modifier
                    .weight(1f)
            )
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right_no_tail),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                tint = LightGrey
            )
        }
    }
}