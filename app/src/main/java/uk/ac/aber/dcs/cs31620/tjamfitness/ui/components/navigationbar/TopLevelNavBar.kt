package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FitnessCenter
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.screens
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.IconGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelNavBar(
    navController: NavController
)
{
    val icons = mapOf(
        Screen.AllSessions to IconGroup(
            filledIcon = Icons.Filled.DateRange,
            outlineIcon = Icons.Outlined.DateRange,
            label = stringResource(id = R.string.all_sessions_label)
        ),
        Screen.AllExercises to IconGroup(
            filledIcon = Icons.Filled.FitnessCenter,
            outlineIcon = Icons.Outlined.FitnessCenter,
            label = stringResource(id = R.string.all_exercises_label)
        ),
    )

    NavigationBar
    {
//        NavigationBarItem(
//            selected = true,
//            onClick = { /*TODO*/ },
//            icon = { Icon(Icons.Filled.DateRange ,"sessions") },
//            label = { Text("Sessions") }
//        )
//
//        NavigationBarItem(
//            selected = false,
//            onClick = { /*TODO*/ },
//            icon = { Icon(Icons.Filled.FitnessCenter,"exercises") },
//            label = { Text("Exercises") }
//        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val l

        screens.forEach {screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route}
        }
    }
}

@Preview
@Composable
fun PreviewTopLevelNavBar()
{
    TopLevelNavBar()
}