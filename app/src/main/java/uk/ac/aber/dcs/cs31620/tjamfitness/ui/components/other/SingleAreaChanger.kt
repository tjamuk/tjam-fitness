package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle

/**
 * Represents a checkbox for a single muscle.
 */
@Composable
fun SingleAreaChanger(
    isCheckboxChecked: Boolean,
    onCheckboxChanged: (Boolean, Int) -> Unit,
    muscle: Muscle
)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        Checkbox(
            checked = isCheckboxChecked,
            onCheckedChange = { isChecked ->
                onCheckboxChanged(isChecked, muscle.ordinal)
            }
        )
        Text(muscle.string)
    }
}