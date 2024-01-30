package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.model.AppViewModel
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem.CombinedClickableListItem
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.navigationbar.TopLevelNavBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.FilterDialog
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.AllExercisesTopAppBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.EditListTopAppBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.SearchExerciseTopBar

/**
 * The page representing a list of ALL exercises, the items are selectable.
 */
@Composable
fun AllExercisesScreen(
    navController: NavController,
    appViewModel: AppViewModel = viewModel(),
) {

    LaunchedEffect(null)
    {
        appViewModel.updateExercises(areSessionExercises = false)
    }

    val exercisesWithDropsets by appViewModel.exercisesWithDropsets.observeAsState(listOf())
    var searchBarInput by remember { mutableStateOf("") }

    var isDialogOpen by remember { mutableStateOf(false) }
    var isFiltering by remember { mutableStateOf(false) }
    var isSearchBarOpen by remember { mutableStateOf(false) }
    var muscleGroupFilters by remember { mutableStateOf<Set<Muscle>>(emptySet()) }

    val allListItems: MutableSet<Int> = mutableSetOf()

    for (exerciseWithDropset in exercisesWithDropsets)
    {
        allListItems.add(exerciseWithDropset.exercise.id)
    }

    var selectedListItems by remember { mutableStateOf<Set<Int>>(emptySet()) }

    var isInSelectionMode by remember { mutableStateOf(false) }

    ConstraintLayout()
    {
        val (columnOfContentsAndTopAppBar, fab, navBar) = createRefs()

        Column(modifier = Modifier.constrainAs(columnOfContentsAndTopAppBar) {top.linkTo(parent.top)} )
        {
            if (isInSelectionMode) {
                EditListTopAppBar(
                    onGoBack = {
                        isInSelectionMode = false
                        selectedListItems = emptySet()
                    },
                    onSelectAll = {
                        selectedListItems =
                            if (selectedListItems.size == exercisesWithDropsets.size) {
                                emptySet()
                            } else {
                                allListItems
                            }
                    },
                    onDelete = {
                        appViewModel.deleteExercises(selectedListItems.toList())
                        isInSelectionMode = false
                        selectedListItems = emptySet()
                    },
                    allSelected = selectedListItems.size == exercisesWithDropsets.size,
                    onEditExercise = {
                        if (selectedListItems.size == 1) {
                            for (exerciseAndDropset in exercisesWithDropsets) {
                                if (selectedListItems.contains(exerciseAndDropset.exercise.id)) {
                                    appViewModel.updateWhichExercise(exerciseAndDropset)
                                    navController.navigate(Screen.OneExercise.route)
                                }
                            }
                        }
                    },
                    isOneItemSelected = selectedListItems.size == 1
                )
            } else if (!isSearchBarOpen) {
                AllExercisesTopAppBar(
                    isFiltering = isFiltering,
                    onToggleFilterDialog = {
                        isDialogOpen = !isDialogOpen
                    },
                    onOpenSearchScreen = {
                        isSearchBarOpen = true
                    },
                )
            } else {
                SearchExerciseTopBar(
                    isFiltering = isFiltering,
                    onGoBack = {
                        isSearchBarOpen = false
                    },
                    onToggleFilterDialog = { isDialogOpen = !isDialogOpen },
                    searchFieldInput = searchBarInput,
                    onValueChange = {
                        searchBarInput = it
                        appViewModel.updateExercises(it, muscleGroupFilters.toList())
                    }
                )
            }

            Text(
                text =
                if (searchBarInput == "")
                    "Not currently searching"
                else
                    "Searching for exercises with name: '$searchBarInput'",
                color = DividerDefaults.color
            )

            Box(modifier = Modifier.fillMaxSize())
            {
                Column()
                {
                    for (exerciseWithDropset in exercisesWithDropsets) {
                        CombinedClickableListItem(
                            headlineTextString = exerciseWithDropset.exercise.name,
                            isSelected = selectedListItems.contains(exerciseWithDropset.exercise.id),
                            onClick = { isNowSelected ->
                                if (isInSelectionMode) {
                                    selectedListItems =
                                        if (isNowSelected) {
                                            selectedListItems.plus(exerciseWithDropset.exercise.id)
                                        } else {
                                            selectedListItems.minus(exerciseWithDropset.exercise.id)
                                        }
                                }
                            },
                            onLongClick = { isNowSelected ->
                                selectedListItems =
                                    if (isNowSelected) {
                                        if (!isInSelectionMode) isInSelectionMode = true

                                        selectedListItems.plus(exerciseWithDropset.exercise.id)
                                    } else {
                                        selectedListItems.minus(exerciseWithDropset.exercise.id)
                                    }
                            },
                            supportingTextString =
                            if (exerciseWithDropset.exercise.hasDropset)
                                "Normal: ${exerciseWithDropset.exercise.sets} sets • ${exerciseWithDropset.exercise.reps} reps • ${exerciseWithDropset.exercise.weight}kg\nDropset: ${exerciseWithDropset.dropset.firstSet}kg • ${exerciseWithDropset.dropset.secondSet}kg • ${exerciseWithDropset.dropset.thirdSet}kg"
                            else
                                "${exerciseWithDropset.exercise.sets} sets • ${exerciseWithDropset.exercise.reps} reps • ${exerciseWithDropset.exercise.weight}kg",
                            imgPath = exerciseWithDropset.exercise.imgPath
                        )
                    }
                }
                FilterDialog(
                    isDialogOpen = isDialogOpen,
                    onToggleDialog = { isDialogOpenNow ->
                        isDialogOpen = isDialogOpenNow
                    },
                    onUpdateFilters = { newMuscleGroupFilters ->

                        muscleGroupFilters = newMuscleGroupFilters
                        isFiltering = newMuscleGroupFilters.isNotEmpty()

                        appViewModel.updateExercises(searchBarInput, muscleGroupFilters.toList())
                    },
                )
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(Screen.CreateExercise.route) },
            modifier = Modifier
                .constrainAs(fab) {
                    bottom.linkTo(navBar.top)
                    end.linkTo(parent.end)
                }
                .padding(end = 15.dp, bottom = 15.dp)
        ) {
            Icon(Icons.Filled.Add, "add exercise")
        }

        TopLevelNavBar(navController = navController, Modifier.constrainAs(navBar) {bottom.linkTo(parent.bottom)})
    }
}