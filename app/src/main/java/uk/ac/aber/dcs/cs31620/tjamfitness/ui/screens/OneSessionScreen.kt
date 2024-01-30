package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.model.AppViewModel
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem.CombinedClickableListItem
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.navigationbar.TopLevelNavBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.EditListTopAppBar

/**
 * The page representing a single session which has a list of exercises. These are selectable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneSessionScreen(
    navController: NavController,
    appViewModel: AppViewModel = viewModel(),
)
{
    val exercisesList by appViewModel.exercises.observeAsState(listOf())

    val allListItems: MutableSet<Int> = mutableSetOf()

    for (exerciseWithDropset in exercisesList)
    {
        allListItems.add(exerciseWithDropset.exercise.id)
    }

    var selectedListItems by remember { mutableStateOf<Set<Int>>(emptySet()) }

    var isInSelectionMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (isInSelectionMode)
                EditListTopAppBar(
                    onGoBack = {
                        isInSelectionMode = false
                        selectedListItems = emptySet()
                    },
                    onSelectAll = {
                        selectedListItems =
                            if (selectedListItems.size == exercisesList.size)
                            {
//                                isInSelectionMode = false
                                emptySet()
                            }
                            else
                            {
                                allListItems
                            }
                    },
                    onDelete = {
                        appViewModel.deleteSessionToExerciseLinks(selectedListItems.toList())
                        isInSelectionMode = false
                        selectedListItems = emptySet()
                    },
                    allSelected = selectedListItems.size == exercisesList.size,
                    onEditExercise = {
                        if (selectedListItems.size == 1)
                        {
                            for (exerciseAndDropset in exercisesList)
                            {
                                if (selectedListItems.contains(exerciseAndDropset.exercise.id))
                                {
                                    appViewModel.updateWhichExercise(exerciseAndDropset)
                                    navController.navigate(Screen.OneExercise.route)
                                }
                            }
                        }
                    },
                    isOneItemSelected = selectedListItems.size == 1
                )
            else
                TopAppBar(
                    title = { Text(appViewModel.whichDay.string) },
                    navigationIcon = {
                        IconButton(
                            content = { Icon(Icons.Filled.ArrowBack,"back") },
                            onClick = { navController.navigateUp() },
                        )
                    }
                )
        },
        bottomBar = { TopLevelNavBar(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddExerciseToSession.route)
                }
            )
            {
                Icon(Icons.Filled.Add, "add exercise")
            }
        }
    )
    { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        )
        {
            items(exercisesList)
            {

                CombinedClickableListItem(
                    headlineTextString = it.exercise.name,
                    isSelected = selectedListItems.contains(it.exercise.id),
                    onClick = {isNowSelected ->
                        if (isInSelectionMode)
                        {
                            selectedListItems =
                                if (isNowSelected)
                                {
                                    selectedListItems.plus(it.exercise.id)
                                }
                                else
                                {
                                    selectedListItems.minus(it.exercise.id)
                                }
                        }
                    },
                    onLongClick = { isNowSelected ->
                        selectedListItems =
                            if (isNowSelected)
                            {
                                if (!isInSelectionMode) isInSelectionMode = true

                                selectedListItems.plus(it.exercise.id)
                            }
                            else
                            {
//                                if (selectedSessions.size == 1) { isInSelectionMode = false }

                                selectedListItems.minus(it.exercise.id)
                            }
                    },
                    supportingTextString =
                        if (it.exercise.hasDropset)
                            "Normal: ${it.exercise.sets} sets • ${it.exercise.reps} reps • ${it.exercise.weight}kg\nDropset: ${it.dropset.firstSet}kg • ${it.dropset.secondSet}kg • ${it.dropset.thirdSet}kg"
                        else
                            "${it.exercise.sets} sets • ${it.exercise.reps} reps • ${it.exercise.weight}kg",
                    imgPath = it.exercise.imgPath
                )
            }
        }
    }
}

