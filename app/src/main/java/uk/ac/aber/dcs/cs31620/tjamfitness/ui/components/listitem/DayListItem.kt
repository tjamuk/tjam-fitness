package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.NonDisposableHandle.parent
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.Session

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DayListItem(
//    session: Session,
//    isSelected: Boolean,
//    onClick: (Int) -> Unit
//)
//{
//    Log.i("day-list-item-tag", "List item for ${session.day.ordinal} recomposed.")
//    ListItem(
////        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = { onClick(session.day.ordinal) }),
//        headlineText = { Text(session.day.string) },
//        supportingText = { Text(session.desc) },
//        trailingContent = {
//            IconButton(
//                content = { Icon(Icons.Filled.ArrowRight, "enter") },
//                onClick = {}
//                //ArrowForward
//            )
//        },
//        leadingContent = {
//            Box {
//                Image(
//                    painter = painterResource(id = R.drawable.full_body_fixed),
//                    contentDescription = "full-body",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(48.dp)
//                        .clip(CircleShape)
//                )
//                if (isSelected)
//                {
//                    Icon(
//                        imageVector = Icons.Filled.CheckCircle,
//                        contentDescription = "Check mark",
//                        tint = Color.Magenta,
//                        modifier = Modifier.align(Alignment.BottomEnd)
//                    )
//                }
//            }
//        },
//        colors =
//            if (isSelected)
//                ListItemDefaults.colors(containerColor = Color.LightGray)
//            else
//                ListItemDefaults.colors()
//
//    )
//    Divider()
//}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DayListItem(
    session: Session,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit,
    onLongClick: (Boolean) -> Unit
)
{
    Log.i("day-list-item-tag", "List item for ${session.day.ordinal} recomposed.")
    ListItem(
//        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        modifier = Modifier
            .fillMaxWidth()
//            .clickable(onClick = { onClick(!isSelected) }),
            .combinedClickable(
                onClick = { onClick(!isSelected) },
                onLongClick = { onLongClick(!isSelected) }
            ),
        headlineText = { Text(session.day.string) },
        supportingText = { Text(session.desc) },
        trailingContent = {
            IconButton(
                content = { Icon(Icons.Filled.ArrowRight, "enter") },
                onClick = {}
                //ArrowForward
            )
        },
        leadingContent = {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.full_body_fixed),
                    contentDescription = "full-body",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                if (isSelected)
                {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircleOutline,
                        contentDescription = "Check mark",
                        tint = Color.Magenta,
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

@Preview
@Composable
fun PreviewDayListItem()
{
    DayListItem(
        Session(
            Day.MONDAY,
            "Rest"
        ),
        isSelected = true,
        onClick = {},
        onLongClick = {}
    )
}