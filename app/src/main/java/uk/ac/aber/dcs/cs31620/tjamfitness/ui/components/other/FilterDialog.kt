package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.AllAreasChanger

@Composable
fun FilterDialog(
    isDialogOpen: Boolean,
    onToggleDialog: (Boolean) -> Unit = {},
    onUpdateFilters: (Set<Int>) -> Unit = {},
) {
//    val muscleGroupFilters = remember {
//        mutableStateListOf<Boolean>().apply {
//            addAll(
//                List(numOfMuscleGroups) {false}
//            )
//        }
//    }

    var muscleGroupFilters by remember { mutableStateOf<Set<Int>>(emptySet()) }


//    val currentFiltersText = StringBuilder("Filters: ")
//
//    for (muscleGroup in muscleGroups)
//    {
//        if (muscleGroup)
//        {
//            currentFiltersText.append(MuscleGroup.)
//        }
//    }
//
//    muscleGroups.forEachIndexed { muscleGroupIndex, isFilteringMuscleGroup ->
//        curren
//    }

    if (isDialogOpen)
    {
        AlertDialog(
            title = { Text("Filter Exercises") },
            text = {
//            AllAreasChanger(
//                onCheckboxChanged = { isChecked, muscleGroupIndex ->
//                    muscleGroupFilters[muscleGroupIndex]
//                },
//                getCheckboxValue = { muscleGroupIndex ->
//                    muscleGroupFilters[muscleGroupIndex]
//                },
//            )

                AllAreasChanger(
                    onCheckboxChanged = { isChecked, muscleGroupOrdinal ->
                        muscleGroupFilters =
                            if (isChecked)
                                muscleGroupFilters.plus(muscleGroupOrdinal)
                            else
                                muscleGroupFilters.minus(muscleGroupOrdinal)
                    },
                    getCheckboxValue = { muscleGroupOrdinal ->
                        muscleGroupFilters.contains(muscleGroupOrdinal)
                    },
                )
            },
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        onToggleDialog(false)
                        // Update the distance to the slider value
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
                        // Use the original distance
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

}




@Preview
@Composable
fun previewFilterDialog()
{
    FilterDialog(true)
}