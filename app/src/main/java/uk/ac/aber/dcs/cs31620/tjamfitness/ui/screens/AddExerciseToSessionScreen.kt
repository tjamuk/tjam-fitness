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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.model.AppViewModel
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem.ClickableListItem
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.FilterDialog
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.AddExerciseTopBar
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.SearchExerciseTopBar

/**
 * The page representing a list of selectable exercise items, clicking one will add it to a session.
 */
@Composable
fun AddExerciseToSessionScreen(
    navController: NavController,
    appViewModel: AppViewModel = viewModel(),
)
{

    LaunchedEffect(null)
    {
        appViewModel.updateExercises()
    }

    val exercisesWithDropsets by appViewModel.exercisesWithDropsets.observeAsState(listOf())
    var searchBarInput by remember { mutableStateOf("") }

    var isDialogOpen by remember { mutableStateOf(false) }
    var isFiltering by remember { mutableStateOf(false) }
    var isSearchBarOpen by remember { mutableStateOf(false) }
    var muscleGroupFilters by remember { mutableStateOf<Set<Muscle>>(emptySet()) }

    Column()
    {
        if (!isSearchBarOpen)
        {
            AddExerciseTopBar(
                isFiltering = isFiltering,
                onGoBack = { navController.navigateUp() },
                onToggleFilterDialog = {
                    isDialogOpen = !isDialogOpen
                },
                onOpenSearchScreen = {
                    isSearchBarOpen = true
                },
            )
        }
        else
        {
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
                for (exerciseWithDropset in exercisesWithDropsets)
                {
                    ClickableListItem(
                        headlineText = exerciseWithDropset.exercise.name,
                        supportingText =
                        if (exerciseWithDropset.exercise.hasDropset)
                            "Normal: ${exerciseWithDropset.exercise.sets} sets • ${exerciseWithDropset.exercise.reps} reps • ${exerciseWithDropset.exercise.weight}kg\nDropset: ${exerciseWithDropset.dropset.firstSet}kg • ${exerciseWithDropset.dropset.secondSet}kg • ${exerciseWithDropset.dropset.thirdSet}kg"
                        else
                            "${exerciseWithDropset.exercise.sets} sets • ${exerciseWithDropset.exercise.reps} reps • ${exerciseWithDropset.exercise.weight}kg",
                        onClick = {
                            appViewModel.addExerciseToSession(exerciseWithDropset.exercise.id)
                            navController.navigateUp()
                        },
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
                    val new = appViewModel.updateExercises(
                        searchBarInput,
                    )
                    Log.i("view-model-tag", "$new")

                    muscleGroupFilters = newMuscleGroupFilters
                    isFiltering = newMuscleGroupFilters.isNotEmpty()

                    appViewModel.updateExercises(searchBarInput, muscleGroupFilters.toList())
                },
            )
            FloatingActionButton(
                onClick = { navController.navigate(Screen.CreateExercise.route) },
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 20.dp, bottom = 20.dp)
            ) {
                Icon(Icons.Filled.Add, "add exercise")
            }
        }
    }
}