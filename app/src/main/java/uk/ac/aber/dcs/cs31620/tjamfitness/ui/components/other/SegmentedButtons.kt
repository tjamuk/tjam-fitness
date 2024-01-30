package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val cornerRadius = 100
private const val firstButtonIndex = 0

/**
 * My pride and joy.
 *
 * Used in create/edit exercise form to select between either (sets, reps or weight (of an exercise)) OR (weights of sets 1,2 and 3 of a dropset (of an exercise))
 */
@Composable
fun SegmentedButtons(
    items: List<String>,
    onItemSelection: (Int) -> Unit,
    horizontalPadding: Dp = 0.dp,
)
{
    val selectedIndex =  remember { mutableStateOf(firstButtonIndex) }

    val lastButtonIndex = items.size - 1

    Row(
        Modifier.fillMaxWidth().padding(horizontal = horizontalPadding)
    ) {
        items.forEachIndexed { index, item ->
            OutlinedButton(
                modifier = Modifier.fillMaxWidth((1F/(items.size-index))),
                onClick = {
                    if (selectedIndex.value != index)
                    {
                        selectedIndex.value = index
                        onItemSelection(selectedIndex.value)
                    }
                },
                shape = when (index)
                {
                    firstButtonIndex ->
                        RoundedCornerShape(
                            topStartPercent = cornerRadius,
                            topEndPercent = 0,
                            bottomStartPercent = cornerRadius,
                            bottomEndPercent = 0,
                        )

                    lastButtonIndex ->
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = cornerRadius,
                            bottomStartPercent = 0,
                            bottomEndPercent = cornerRadius,
                        )

                    else ->
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomStartPercent = 0,
                            bottomEndPercent = 0,
                        )
                },
                colors =
                    if (selectedIndex.value == index)
                    {
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.LightGray
                        )
                    }
                    else
                    {
                        ButtonDefaults.outlinedButtonColors()
                    }
            )
            {
                if (selectedIndex.value == index)
                {
                    IconAndText(
                        Icons.Filled.Check,
                        item
                    )
                }
                else
                {
                    Text(item)
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewSegmentedButton()
{
    SegmentedButtons(
        items = listOf("Sets","Reps", "Weight"),
        onItemSelection = {},
        horizontalPadding = 10.dp
    )
}