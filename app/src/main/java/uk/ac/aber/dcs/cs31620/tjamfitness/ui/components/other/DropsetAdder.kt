package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Used in an edit/create exercise screen.
 *
 * For adding a dropset and changing the values of the weights of its sets.
 */
@Composable
fun DropsetAdder(
    isCheckboxChecked: Boolean,
    onCheckboxChanged: (Boolean) -> Unit,
    selectedSetIndex: Int,
    onChangeSelectedSetIndex: (Int) -> Unit,
    getSetValue: (Int) -> Int,
    onChangeSelectedSetValue: (Int) -> Unit,
)
{
    var isDropdownEnabled = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        Checkbox(
            checked = isCheckboxChecked,
            onCheckedChange = { isChecked ->
                onCheckboxChanged(isChecked)
            }
        )
        Text("Add Dropset?")
        IconButton(
            onClick = {
                isDropdownEnabled.value = !(isDropdownEnabled.value)
            },
            content = {
                Icon(
                    imageVector =
                    if (isDropdownEnabled.value)
                        Icons.Filled.KeyboardArrowDown
                    else
                        Icons.Filled.KeyboardArrowRight,
                    contentDescription = "show muscles",
                )
            },
        )
    }
    if (isDropdownEnabled.value)
    {
        NumbersSection(
            selectedNumIndex = selectedSetIndex,
            onChangeSelectedNumIndex = onChangeSelectedSetIndex,
            getNumValue = getSetValue,
            onChangeSelectedNumValue = onChangeSelectedSetValue,
            isNumbersSection = false,
        )
    }
}