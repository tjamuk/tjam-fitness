package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.FilterAltOff
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * The normal top app bar for the all exercises screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllExercisesTopAppBar(
    isFiltering: Boolean,
    onToggleFilterDialog: () -> Unit,
    onOpenSearchScreen: () -> Unit,
)
{
    CenterAlignedTopAppBar(
        title = { Text("All Exercises") },
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Outlined.Search, "search") },
                onClick = onOpenSearchScreen
            )
        },
        actions = {
            IconButton(
                content = {
                    Icon(
                        imageVector =
                        if (isFiltering)
                            Icons.Outlined.FilterAlt
                        else
                            Icons.Outlined.FilterAltOff,
                        contentDescription = "open filter menu"
                    )
                },
                onClick = onToggleFilterDialog
            )
        }
    )
}

