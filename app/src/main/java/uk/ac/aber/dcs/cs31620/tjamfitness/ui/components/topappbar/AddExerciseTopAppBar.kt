package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.FilterAltOff
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

/**
 * The normal top app bar for the add exercise to session screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseTopBar(
    isFiltering: Boolean,
    onGoBack: () -> Unit,
    onToggleFilterDialog: () -> Unit,
    onOpenSearchScreen: () -> Unit,
)
{
    TopAppBar(
        title = { Text("Add Exercise") },
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Filled.ArrowBack,"back") },
                onClick = onGoBack
            )
        },
        actions = {
            IconButton(
                content = { Icon(Icons.Outlined.Search, "search") },
                onClick = onOpenSearchScreen
            )
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