package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.Session
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem.DayListItem
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.navigationbar.TopLevelNavBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.centeraligned.TopLevelTopAppBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.small.EditSessionsTopAppBar

val sessions = listOf(
    Session(
        day = Day.MONDAY,
        desc = "Rest"
    ),
    Session(
        day = Day.TUESDAY,
        desc = "Chest"
    ),
    Session(
        day = Day.WEDNESDAY,
        desc = "Bicep"
    )
)

val setOfAllSessions = (sessions.indices).toSet()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllSessionsScreen(
    navController: NavController
)
{
    Log.i("day-list-item-tag", "AllSessionsScreen recomposed")

    var selectedSessions by remember { mutableStateOf<Set<Int>>(emptySet()) }

    var isInSelectionMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (isInSelectionMode)
                EditSessionsTopAppBar(
                    onGoBack = {
                        isInSelectionMode = false
                        selectedSessions = emptySet()
                    },
                    onSelectAll = {
                        selectedSessions =
                            if (selectedSessions.size == sessions.size)
                            {
//                                isInSelectionMode = false
                                emptySet()
                            }
                            else
                            {
                                setOfAllSessions
                            }
                    },
                    onDelete = {},
                    allSelected = selectedSessions.size == sessions.size
                )
            else
                TopLevelTopAppBar(title = stringResource(id = R.string.weekly_sessions_title))
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
            items(sessions)
            {
                DayListItem(
                    session = it,
                    isSelected = selectedSessions.contains(it.day.ordinal),
                    onClick = {isNowSelected ->
                        if (isInSelectionMode)
                        {
                            selectedSessions =
                                if (isNowSelected)
                                {
                                    selectedSessions.plus(it.day.ordinal)
                                }
                                else
                                {
//                                    if (selectedSessions.size == 1) { isInSelectionMode = false }

                                    selectedSessions.minus(it.day.ordinal)
                                }
                        }
                    },
                    onLongClick = { isNowSelected ->
                        selectedSessions =
                            if (isNowSelected)
                            {
                                if (!isInSelectionMode) isInSelectionMode = true

                                selectedSessions.plus(it.day.ordinal)
                            }
                            else
                            {
//                                if (selectedSessions.size == 1) { isInSelectionMode = false }

                                selectedSessions.minus(it.day.ordinal)
                            }
                    }
                )
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AllSessionsScreen(
//    navController: NavController
//)
//{
//    Log.i("day-list-item-tag", "AllSessionsScreen recomposed")
//
//    val selectedSessions by remember { mutableStateOf(BooleanArray(7)) }
//
//    Scaffold(
//        topBar = { TopLevelTopAppBar(title = stringResource(id = R.string.weekly_sessions_title)) },
//        bottomBar = { TopLevelNavBar(navController = navController)},
//    )
//    { innerPadding ->
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth().padding(innerPadding),
//        )
//        {
//            items(sessions)
//            {
//                DayListItem(
//                    session = it,
//                    isSelected = selectedSessions[it.day.ordinal],
//                    onClick = {index ->
//                        selectedSessions[index] = !selectedSessions[index]
//                        sessions[index]
//                        Log.i("day-list-item-tag", "Session ${index} selected == ${selectedSessions[index]}"
//                        )
//                    }
//                )
//            }
//        }
//    }
//}