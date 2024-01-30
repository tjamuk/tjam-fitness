package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

/**
 * The top app bar that is used on a screen/page with a list with selectable items.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditListTopAppBar(
    onGoBack: () -> Unit,
    onSelectAll: (Boolean) -> Unit,
    onDelete: () -> Unit,
    allSelected: Boolean,
    onEditExercise: () -> Unit = {},
    isOneItemSelected: Boolean = false,
    isForExerciseList: Boolean = true,
    )
{
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Filled.ArrowBack,"back") },
                onClick = onGoBack
            )
        },
        actions = {
            Text("Select All")
            Checkbox(checked = allSelected, onCheckedChange = onSelectAll)

            if (isForExerciseList)
            {
                IconButton(
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "edit exercise",
                        )
                    },
                    onClick = onEditExercise,
                    enabled = isOneItemSelected
                )
            }

            IconButton(
                content = { Icon(Icons.Filled.Delete, "enter") },
                onClick = onDelete
            )
        }
    )
}