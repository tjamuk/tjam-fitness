package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.small

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneExerciseTopAppBar(
    isCreatingExercise: Boolean = true //this page can be either used for creating exercises or editting them, to tell which this boolean is used.
)
{
    TopAppBar(
        title = {
            if (isCreatingExercise)
            {
                Text("Create Exercise")
            }
            else
            {
                Text("Edit Exercise")
            }
        },
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Filled.ArrowBack,"back") },
                onClick = { }
            )
        },
        actions = {

            TextButton(
                onClick = { /*TODO*/ },
                content = {
                    Icon(Icons.Outlined.Check, "enter")
                    Text("Confirm")
                }
            )
        }
    )
}

@Preview
@Composable
fun PreviewOneExerciseTopAppBar()
{
    OneExerciseTopAppBar()
}