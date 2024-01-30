package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * The normal top app bar that is used on the edit exercise screen/page.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneExerciseTopAppBar(
    isCreatingExercise: Boolean = true, //this page can be either used for creating exercises or editting them, to tell which this boolean is used.
    isFormComplete: Boolean,
    onGoBack: () -> Unit = {},
    onSubmitForm: () -> Unit = {}
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
                onClick = onGoBack,
            )
        },
        actions = {

            TextButton(
                onClick = onSubmitForm,
                content = {
                    Icon(Icons.Outlined.Check, "enter")
                    Text("Confirm")
                },
                enabled = isFormComplete,
            )
        }
    )
}

@Preview
@Composable
fun PreviewOneExerciseTopAppBar()
{
    OneExerciseTopAppBar(true,true)
}