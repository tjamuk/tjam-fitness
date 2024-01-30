package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uk.ac.aber.dcs.cs31620.tjamfitness.R

/**
 * A list item that is typically for something that only needs a single click.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ClickableListItem(
    headlineText: String,
    supportingText: String,
    onClick: () -> Unit = {},
    imgPath: String = "",
    isSessionListItem: Boolean = false
)
{
    ListItem(
        headlineText = { Text(headlineText) },
        supportingText = { Text(supportingText) },
        leadingContent = {
            GlideImage(
                model =
                if (imgPath != "")
                    Uri.parse(imgPath)
                else
                    Uri.parse(stringResource(R.string.default_image)),
                contentDescription = "image",
                modifier = Modifier.size(48.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        },
        trailingContent = {
            if (isSessionListItem)
            {
                Icon(Icons.Filled.ArrowRight, "enter")
            }
        },
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)
    )
    Divider()
}