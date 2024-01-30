package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uk.ac.aber.dcs.cs31620.tjamfitness.R

/**
 * A list item that is typically for something that that has a long click and quick click.
 *
 * Used where there are selectable lists.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalGlideComposeApi::class
)
@Composable
fun CombinedClickableListItem(
    headlineTextString: String,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit,
    onLongClick: (Boolean) -> Unit,
    supportingTextString: String = "Rest",
    imgPath: String = "",
    isSessionListItem: Boolean = false,
)
{
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onClick(!isSelected) },
                onLongClick = { onLongClick(!isSelected) }
            ),
        headlineText = { Text(headlineTextString) },
        supportingText = { Text(supportingTextString) },
        trailingContent = {
            if (isSessionListItem)
            {
                Icon(Icons.Filled.ArrowRight, "enter")
            }
        },
        leadingContent = {
            Box {
                GlideImage(
                    model =
                        if (imgPath != "")
                            Uri.parse(imgPath)
                        else
                            Uri.parse(stringResource(R.string.default_image)),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                if (isSelected)
                {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Check mark",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
            }
        },
        colors =
            if (isSelected)
                ListItemDefaults.colors(containerColor = Color.LightGray)
            else
                ListItemDefaults.colors()
    )
    Divider()
}