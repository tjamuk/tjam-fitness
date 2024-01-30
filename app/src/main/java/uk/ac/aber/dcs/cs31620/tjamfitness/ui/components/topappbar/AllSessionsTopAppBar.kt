package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * The normal top app bar for the all sessions screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllSessionsTopAppBar(
    title: String,
)
{
    CenterAlignedTopAppBar(
        title = { Text(title) }
    )
}