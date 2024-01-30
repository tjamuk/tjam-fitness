package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.LooksOne
import androidx.compose.material.icons.outlined.LooksTwo
import androidx.compose.material.icons.outlined.Loop
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.rounded.RepeatOne
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.WEIGHT_INDEX

/**
 * Contains the section that changes the values of either the sets, reps and weight of an exercise OR the weights of the 3 sets of its dropset
 */
@Composable
fun NumbersSection(
    selectedNumIndex: Int,
    onChangeSelectedNumIndex: (Int) -> Unit,
    getNumValue: (Int) -> Int,
    onChangeSelectedNumValue: (Int) -> Unit,
    isNumbersSection: Boolean,
)
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (isNumbersSection)
        {
            IconAndText(Icons.Outlined.Loop,"${getNumValue(0)} Sets")
            IconAndText(Icons.Rounded.RepeatOne, "${getNumValue(1)} Reps")
            IconAndText(Icons.Outlined.Scale, "${getNumValue(2)}kg")
        }
        else
        {
            IconAndText(Icons.Outlined.LooksOne,"${getNumValue(0)}kg")
            IconAndText(Icons.Outlined.LooksTwo, "${getNumValue(1)}kg")
            IconAndText(Icons.Outlined.Looks3, "${getNumValue(2)}kg")
        }
    }

    SegmentedButtons(
        items =
        if (isNumbersSection)
            listOf("Sets","Reps", "Weight")
        else
            listOf("Set 1", "Set 2", "Set 3"),
        onItemSelection = onChangeSelectedNumIndex,
        horizontalPadding = 10.dp,
    )

    ChangeNumbers(
        selectedNumValue = getNumValue(selectedNumIndex),
        selectedNumIndex =
        if (isNumbersSection)
            selectedNumIndex
        else
            WEIGHT_INDEX,
        onChangeSelectedNumValue = onChangeSelectedNumValue,
    )
}