package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.centeraligned.TopLevelTopAppBar

@Composable
fun WeeklySessionsScreen(
    navController: NavController
)
{
    Scaffold(
        topBar = { TopLevelTopAppBar(title = stringResource(id = R.string.weekly_sessions_title)) },
        bottomBar = { TopLevelBottomAppBar() }
    )
}