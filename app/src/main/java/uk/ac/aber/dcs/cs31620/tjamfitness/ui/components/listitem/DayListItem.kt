package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.NonDisposableHandle.parent
import uk.ac.aber.dcs.cs31620.tjamfitness.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayListItem(
    headlineText: String,
    supportingText: String
)
{
    ListItem(
        headlineText = { Text(headlineText) },
        supportingText = { Text(supportingText) },
        trailingContent = {
            IconButton(
                content = { Icon(Icons.Filled.ArrowRight, "enter") },
                onClick = {}
                //ArrowForward
            )
        },
        leadingContent = {
            Image(
                painter = painterResource(id = R.drawable.full_body_fixed),
                contentDescription = "full-body",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
    Divider()
}

@Preview
@Composable
fun PreviewDayListItem()
{
    DayListItem(
        headlineText = "Monday",
        supportingText = "Full-body • 1h22"
    )
}