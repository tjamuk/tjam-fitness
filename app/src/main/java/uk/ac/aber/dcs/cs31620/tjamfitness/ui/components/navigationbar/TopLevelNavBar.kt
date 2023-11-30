package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.aber.dcs.cs31620.tjamfitness.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelNavBar(
)
{
//    BottomAppBar(
//        modifier = Modifier.align(Alignment.Center),
//        actions = {
//
//            BottomAppBarItem(
//                icon_painter = painterResource(R.drawable.sessions),
//                icon_desc = "Sessions",
//                title = "Sessions"
//            )
//
//            BottomAppBarItem(
//                icon_painter = painterResource(R.drawable.exercises),
//                icon_desc = "Exercises",
//                title = "Exercises"
//            )
//        }
//    )

    NavigationBar()
    {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Filled.DateRange ,"sessions") },
            label = { Text("Sessions") }
        )

        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Filled.FitnessCenter,"exercises") },
            label = { Text("Exercises") }
        )
    }
}

@Preview
@Composable
fun PreviewTopLevelNavBar()
{
    TopLevelNavBar()
}