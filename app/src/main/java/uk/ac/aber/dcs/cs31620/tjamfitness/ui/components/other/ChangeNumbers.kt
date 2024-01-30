package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * the units for a number, [0] = sets, [1] = reps, [2] = weight (inc. the weights of sets of a dropset)
 */
val units: Array<String> =
    arrayOf(
        "Sets",
        "Reps",
        "kg"
    )

/**
 * Represents a number changer for the values of the sets, reps or weights of an exercise (or weights of one of the sets of its dropset)
 */
@Composable
fun ChangeNumbers(
    selectedNumValue: Int,
    selectedNumIndex: Int,
    onChangeSelectedNumValue: (Int) -> Unit,
)
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {
        AdderButtons(
            selectedNumIndex = selectedNumIndex,
            positive = false,
            onChangeSelectedNumValue = onChangeSelectedNumValue,
        )

        Text(
            text = "${selectedNumValue}\n${units[selectedNumIndex]}",
            textAlign = TextAlign.Center
        )

        AdderButtons(
            selectedNumIndex = selectedNumIndex,
            positive = true,
            onChangeSelectedNumValue = onChangeSelectedNumValue,
        )
    }
}