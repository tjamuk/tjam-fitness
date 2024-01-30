package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.FilterAltOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A top bar used for searching for an exercise.
 *
 * Typically used on screens with a list of exercises (not single session page)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchExerciseTopBar(
    isFiltering: Boolean,
    onGoBack: () -> Unit,
    onToggleFilterDialog: () -> Unit,
    searchFieldInput: String,
    onValueChange: (String) -> Unit
)
{
    TopAppBar(
        title = { SearchField(searchFieldInput,onValueChange) },
        navigationIcon = {
            IconButton(
                content = { Icon(Icons.Filled.ArrowBack,"back") },
                onClick = onGoBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    searchFieldInput: String,
    onValueChange: (String) -> Unit
)
{
    OutlinedTextField(
        value = searchFieldInput,
        onValueChange = {
            onValueChange(it)
        },
        trailingIcon = {
            IconButton(
                content = { Icon(Icons.Outlined.Clear, "clear text field") },
                onClick = {onValueChange("")}
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        shape = RoundedCornerShape(100)
    )
}

//@Preview
//@Composable
//fun PreviewAddExerciseTopAppBar()
//{
//    AddExerciseTopBar(
//        false, {}, {}, {}
//    )
//}