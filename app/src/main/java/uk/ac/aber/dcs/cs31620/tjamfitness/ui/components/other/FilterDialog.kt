package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.muscles

/**
 * Used in a screen showing a list of exercises. Filters those exercises by muscles worked by exercises.
 *
 * This is an alert dialog contains 2 columns of checkboxes of muscles.
 *
 * Selecting a checkbox means you're showing exercises of that exercise.
 */
@Composable
fun FilterDialog(
    isDialogOpen: Boolean,
    onToggleDialog: (Boolean) -> Unit = {},
    onUpdateFilters: (Set<Muscle>) -> Unit = {},
) {

    var muscleGroupFilters by remember { mutableStateOf<Set<Muscle>>(emptySet()) }

    if (isDialogOpen)
    {
        AlertDialog(
            title = { Text("Filter Exercises") },
            text = {
                AllAreasChanger(
                    onCheckboxChanged = { isChecked, muscleOrdinal ->
                        muscleGroupFilters =
                            if (isChecked)
                                muscleGroupFilters.plus(muscles[muscleOrdinal])
                            else
                                muscleGroupFilters.minus(muscles[muscleOrdinal])
                    },
                    getCheckboxValue = { muscleOrdinal ->
                        muscleGroupFilters.contains(muscles[muscleOrdinal])
                    },
                )
            },
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        onToggleDialog(false)
                        onUpdateFilters(muscleGroupFilters)
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onToggleDialog(false)
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

}