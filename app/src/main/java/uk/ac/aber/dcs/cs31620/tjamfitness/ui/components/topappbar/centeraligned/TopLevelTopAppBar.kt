package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.centeraligned

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelTopAppBar(
    title: String,
)
{
    CenterAlignedTopAppBar(
        title = { Text(title) }
    )
}

@Preview
@Composable
fun PreviewTopLevelTopAppBar()
{
    TopLevelTopAppBar("Exercises")
}