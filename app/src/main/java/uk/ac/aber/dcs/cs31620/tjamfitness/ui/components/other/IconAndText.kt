package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Just used in an non-interactive icon next to text component.
 */
@Composable
fun IconAndText(
    imageVector: ImageVector,
    text: String,
)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector,
            text,
        )
        Text(text)
    }
}