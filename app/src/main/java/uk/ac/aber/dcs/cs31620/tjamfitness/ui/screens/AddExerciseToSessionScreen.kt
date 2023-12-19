package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.FilterDialog
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.small.AddExerciseTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseToSessionScreen(
    navController: NavController? = null,
) {

    var isDialogOpen by remember { mutableStateOf(false) }
    var isFiltering by remember { mutableStateOf(false) }
    var muscleGroupFilters by remember { mutableStateOf<Set<Int>>(emptySet()) }

    Scaffold(
        topBar = {
            AddExerciseTopBar(
                isFiltering = isFiltering,
                onGoBack = { /*TODO*/ },
                onToggleFilterDialog = {
                    isDialogOpen = !isDialogOpen
                },
                onOpenSearchScreen = { /*TODO*/ },
            )
        },
    )
    { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
        {
            Box()
            {
                Column()
                {
                    Text("Hello world")
                }
                FilterDialog(
                    isDialogOpen = isDialogOpen,
                    onToggleDialog = { isDialogOpenNow ->
                         isDialogOpen = isDialogOpenNow
                    },
                    onUpdateFilters = { newMuscleGroupFilters ->
                        muscleGroupFilters = newMuscleGroupFilters

                        isFiltering = !newMuscleGroupFilters.isEmpty()
                    },
                )
            }
        }
    }
}
