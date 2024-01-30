package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.topLevelScreens
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.IconGroup

/**
 * The top level navigation bar that is used on many screens.
 *
 * It can either go to the all sessions or all exercises pages.
 */
@Composable
fun TopLevelNavBar(
    navController: NavController,
    modifier: Modifier = Modifier
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

    NavigationBar(modifier = modifier)
    {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        topLevelScreens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val labelText = icons[screen]!!.label
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = (if (isSelected)
                            icons[screen]!!.filledIcon
                        else
                            icons[screen]!!.outlineIcon),
                        contentDescription = labelText
                    )
                },
                label = { Text(labelText) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}
