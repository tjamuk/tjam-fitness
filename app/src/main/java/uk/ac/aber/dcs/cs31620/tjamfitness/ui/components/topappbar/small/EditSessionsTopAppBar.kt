package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.small

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.aber.dcs.cs31620.tjamfitness.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSessionsTopAppBar()
{
    TopAppBar(
        title = { Text("") },
        navigationIcon = { Icon(Icons.Filled.ArrowBack,"back") },
        actions = {
            Text("Select All")
            Checkbox(checked = false, onCheckedChange = {})

            IconButton(
                content = { Icon(Icons.Filled.Title, "enter") },
                onClick = {}
            )
            IconButton(
                content = { Icon(Icons.Filled.Delete, "enter") },
                onClick = {}
            )

        }

    )
}

@Preview
@Composable
fun PreviewEditSessionsTopAppBar()
{
    EditSessionsTopAppBar()
}