package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.muscles
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.numOfMuscles
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.numOfMusclesHalved

/**
 * 2 columns of checkboxes, each for a different muscle
 */
@Composable
fun AllAreasChanger(
    onCheckboxChanged: (Boolean, Int) -> Unit,
    getCheckboxValue: (Int) -> Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    )
    {
        Column()
        {
            for (muscleGroupIndex in 0 until numOfMusclesHalved) {
                SingleAreaChanger(
                    isCheckboxChecked = getCheckboxValue(muscleGroupIndex),
                    onCheckboxChanged = onCheckboxChanged,
                    muscle = muscles[muscleGroupIndex]
                )
            }
        }
        Column()
        {
            for (muscleGroupIndex in numOfMusclesHalved..(numOfMuscles - 1)) {
                SingleAreaChanger(
                    isCheckboxChecked = getCheckboxValue(muscleGroupIndex),
                    onCheckboxChanged = onCheckboxChanged,
                    muscle = muscles[muscleGroupIndex]
                )
            }
        }
    }
}