package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListItem(
    headlineText: String,
    supportingText: String
)
{
    ListItem(
        headlineText = { Text(headlineText) },
        supportingText = { Text(supportingText) },
        modifier = Modifier.fillMaxWidth()
    )
    Divider()
}

@Preview
@Composable
fun PreviewExerciseListItem()
{
    ExerciseListItem(
        headlineText = "Bicep Curl",
        supportingText = "5 sets • 12 reps • 10kg"
    )
}