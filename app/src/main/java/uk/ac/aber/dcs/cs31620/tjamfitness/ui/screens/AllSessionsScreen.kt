package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.model.AppViewModel
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.days
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem.CombinedClickableListItem
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem.ClickableListItem
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.navigationbar.TopLevelNavBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.AllSessionsTopAppBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.EditListTopAppBar

/**
 * The page representing a list of ALL sessions, the items are selectable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllSessionsScreen(
    navController: NavController,
    appViewModel: AppViewModel = viewModel()
)
{
    val sessionsMap by appViewModel.sessionsMap.observeAsState(mapOf())

    var selectedSessions by remember { mutableStateOf<Set<Int>>(emptySet()) }

    val setOfAllSessions = mutableSetOf<Int>()

    sessionsMap.forEach {
        setOfAllSessions.add(it.key.ordinal)
    }

    var isInSelectionMode by remember { mutableStateOf(false) }

    var doesDayHaveSession: Boolean

    Scaffold(
        topBar = {
            if (isInSelectionMode)
                EditListTopAppBar(
                    onGoBack = {
                        isInSelectionMode = false
                        selectedSessions = emptySet()
                    },
                    onSelectAll = {
                        selectedSessions =
                            if (selectedSessions.size == sessionsMap.size)
                            {
                                emptySet()
                            }
                            else
                            {
                                setOfAllSessions
                            }
                    },
                    onDelete = {
                        appViewModel.deleteSessions(selectedSessions.toList())
                        isInSelectionMode = false
                        selectedSessions = emptySet()
                    },
                    allSelected = selectedSessions.size == sessionsMap.size,
                    isForExerciseList = false
                )
            else
                AllSessionsTopAppBar(title = stringResource(id = R.string.weekly_sessions_title))
        },
        bottomBar = { TopLevelNavBar(navController = navController)},
    )
    { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        )
        {
            items(days)
            {

                doesDayHaveSession = sessionsMap.containsKey(it)

                if (doesDayHaveSession)
                {
                    CombinedClickableListItem(
                        headlineTextString = it.string,
                        isSelected = selectedSessions.contains(it.ordinal),
                        onClick = { isNowSelected ->
                            if (isInSelectionMode)
                            {
                                selectedSessions =
                                    if (isNowSelected)
                                    {
                                        selectedSessions.plus(it.ordinal)
                                    }
                                    else
                                    {
                                        selectedSessions.minus(it.ordinal)
                                    }
                            }
                            else
                            {
                                appViewModel.updateWhichDay(it)
                                navController.navigate(Screen.OneSession.route)
                            }
                        },
                        onLongClick = { isNowSelected ->
                            selectedSessions =
                                if (isNowSelected)
                                {
                                    if (!isInSelectionMode) isInSelectionMode = true

                                    selectedSessions.plus(it.ordinal)
                                }
                                else
                                {
                                    selectedSessions.minus(it.ordinal)
                                }
                        },
                        supportingTextString = sessionsMap.getValue(it).description,
                        imgPath = sessionsMap.getValue(it).imgPath,
                        isSessionListItem = true
                    )
                }
                else
                {
                    ClickableListItem(
                        headlineText = it.string,
                        supportingText = "Rest",
                        onClick = {
                            if (!isInSelectionMode)
                            {
                                appViewModel.updateWhichDay(it)
                                navController.navigate(Screen.OneSession.route)
                            }
                        },
                        isSessionListItem = true
                    )
                }
            }
        }
    }
}
