package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.NAME_CHAR_MAX
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.NAME_CHAR_MIN

/**
 * On a edit/create exercise form, this is a textField used to change the exercise's name
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(
    name: String,
    onValueChange: (String) -> Unit
)
{
    val isNameBadLength: Boolean = (name.length !in NAME_CHAR_MIN..NAME_CHAR_MAX)

    OutlinedTextField(
        label = { Text("Name") },
        value = name,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text("Enter a name") },
        isError = isNameBadLength,
        supportingText = {
            if (isNameBadLength)
            {
                Text("At least $NAME_CHAR_MIN and at most $NAME_CHAR_MAX characters")
            }
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
            .padding(start = 10.dp)
    )
}