package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * There are values that when you press a button add to the [0] sets, [1] reps. [2] weight
 */
val adderValues: Array<Array<Int>> =
    arrayOf(
        arrayOf(1,3,5),
        arrayOf(1,4,10),
        arrayOf(1,5,20)
    )

/**
 * Represent a column of 3 buttons.
 *
 * Appear when creating or editing an exercise's dropset set weights [3] or its sets [0], reps [1] and weights [2]
 *
 * These buttons are either negative (subtract from sumbers) or positive (add to the numbers)
 */
@Composable
fun AdderButtons(
    selectedNumIndex: Int,
    positive: Boolean,
    onChangeSelectedNumValue: (Int) -> Unit,
)
{
    Column()
    {
        for (value in adderValues[selectedNumIndex])
        {
            OutlinedButton(
                onClick = {
                    onChangeSelectedNumValue(
                        if (positive)
                            value
                        else
                            -value
                    )
                },
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp)

            )
            {
                Text(
                    text =
                    if (positive)
                        "+$value"
                    else
                        "−$value",
                    fontSize = 20.sp,
                    softWrap = false,
                    overflow = TextOverflow.Visible,
                )
            }
        }
    }

}