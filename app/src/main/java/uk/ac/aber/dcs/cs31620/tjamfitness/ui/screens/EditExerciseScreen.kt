package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.outlined.FilterTiltShift
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.model.AppViewModel
import uk.ac.aber.dcs.cs31620.tjamfitness.ImagePicker
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.muscles
import uk.ac.aber.dcs.cs31620.tjamfitness.model.ExerciseWithDropset
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.AllAreasChanger
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.DropsetAdder
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.NameField
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.NumbersSection
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.SectionDivider
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.OneExerciseTopAppBar

const val NAME_CHAR_MAX = 25
const val NAME_CHAR_MIN = 2

/**
 * [0] bounds for set values, [1] reps, [2] weight
 */
val numberBounds: Array<Pair<Int, Int>> = arrayOf(
    Pair(1, 12),
    Pair(1, 25),
    Pair(0, 777),
)

/**
 * For all constants and values like numberBounds, this is used to show the index representing weights (used for the weight of an exercise or those of a set of a dropset of an exercise)
 */
const val WEIGHT_INDEX = 2

/**
 * The page representing a form for editing an existing exercise.
 */
@Composable
fun OneExerciseScreenTopLevel(
    navController: NavController,
    appViewModel: AppViewModel = viewModel()
)
{
    val oldMuscles by appViewModel.exerciseMuscles.observeAsState(listOf())

    oldMuscles.let {
        EditExerciseScreen(
            navController = navController,
            whichExerciseWithDropset = appViewModel.whichExercise,
            oldMuscles = it.toSet(),
            updateExercise = { exerciseEntity: ExerciseEntity, firstSet: Int?, secondSet: Int?, thirdSet: Int?, newMuscles: Set<Muscle>, oldMuscles: Set<Muscle> ->
                appViewModel.updateSingleExercise(exerciseEntity, firstSet, secondSet, thirdSet, newMuscles, oldMuscles)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditExerciseScreen(
    navController: NavController,
    whichExerciseWithDropset: ExerciseWithDropset?,
    oldMuscles: Set<Muscle>,
    updateExercise: (ExerciseEntity, Int?, Int?, Int?, Set<Muscle>, Set<Muscle>) -> Unit
)
{
    whichExerciseWithDropset?.let {
        var name by remember { mutableStateOf(it.exercise.name) }

        var selectedMuscles by remember { mutableStateOf<Set<Muscle>>(oldMuscles) }
        LaunchedEffect(oldMuscles) // need this because for some reason sometimes selectedMuscles is empty when oldMuscles isn't?
        {
            selectedMuscles = oldMuscles
        }

        var isDropsetEnabled by remember { mutableStateOf(it.exercise.hasDropset) }
        var selectedSetIndex by remember { mutableStateOf(0) }
        val dropsetValues = remember {
            mutableStateListOf<Int>().apply { addAll(listOf(it.dropset.firstSet,it.dropset.secondSet,it.dropset.thirdSet)) }
        }

        var selectedNumIndex by remember { mutableStateOf(0) }
        val numbersValues = remember {
            mutableStateListOf<Int>().apply { addAll(listOf(it.exercise.sets, it.exercise.reps, it.exercise.weight)) }
        }

        var imageUri by remember { mutableStateOf<Uri?>(Uri.parse(it.exercise.imgPath)) }

        var newValue: Int

        Scaffold(
            topBar = {
                OneExerciseTopAppBar(
                    isCreatingExercise = false,
                    isFormComplete = (name.length in NAME_CHAR_MIN..NAME_CHAR_MAX),
                    onGoBack = { navController.navigateUp() },
                    onSubmitForm = {
                        updateExercise(
                            ExerciseEntity(
                                it.exercise.id,
                                name,
                                imageUri.toString(),
                                numbersValues[0],
                                numbersValues[1],
                                numbersValues[2],
                                isDropsetEnabled,
                            ),
                            dropsetValues[0],
                            dropsetValues[1],
                            dropsetValues[2],
                            selectedMuscles,
                            oldMuscles,
                        )
                        navController.navigateUp()
                    }
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
                SectionDivider(imageVector = Icons.Outlined.Info, title = "Basic Information")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
                    ImagePicker(
                        imageUri = imageUri,
                        onImageUpload = {
                            if (it != null)
                            {
                                imageUri = it
                            }
                        }
                    )

                    NameField(
                        name = name,
                        onValueChange = {name = it}
                    )
                }

                SectionDivider(imageVector = Icons.Outlined.Numbers, title = "Numbers")

                NumbersSection(
                    selectedNumIndex = selectedNumIndex,
                    onChangeSelectedNumIndex = {selectedNumIndex = it},
                    getNumValue = { numbersValues[it] },
                    onChangeSelectedNumValue = { incrementValue ->
                        newValue = numbersValues[selectedNumIndex]+incrementValue

                        numbersValues[selectedNumIndex] =
                            if (newValue > numberBounds[selectedNumIndex].second)
                            {
                                numberBounds[selectedNumIndex].second
                            }
                            else if (newValue < numberBounds[selectedNumIndex].first)
                            {
                                numberBounds[selectedNumIndex].first
                            }
                            else
                            {
                                newValue
                            }
                    },
                    isNumbersSection = true,
                )

                SectionDivider(imageVector = Icons.Outlined.FilterTiltShift, title = "Muscle Groups")

                AllAreasChanger(
                    onCheckboxChanged = { isChecked, muscleGroupOrdinal ->
                        selectedMuscles =
                            if (isChecked)
                                selectedMuscles.plus(muscles[muscleGroupOrdinal])
                            else
                                selectedMuscles.minus(muscles[muscleGroupOrdinal])
                    },
                    getCheckboxValue = { muscleGroupOrdinal ->
                        selectedMuscles.contains(muscles[muscleGroupOrdinal])
                    },
                )

                SectionDivider(imageVector = Icons.Filled.TrendingDown, title = "Dropset")

                DropsetAdder(
                    isCheckboxChecked = isDropsetEnabled,
                    onCheckboxChanged = { isDropsetEnabled = it },
                    selectedSetIndex = selectedSetIndex,
                    onChangeSelectedSetIndex = {selectedSetIndex = it},
                    getSetValue = { dropsetValues[it] },
                    onChangeSelectedSetValue = { incrementValue ->

                        newValue = dropsetValues[selectedSetIndex]+incrementValue

                        dropsetValues[selectedSetIndex] =
                            if (newValue > numberBounds[WEIGHT_INDEX].second)
                            {
                                numberBounds[WEIGHT_INDEX].second
                            }
                            else if (newValue < numberBounds[WEIGHT_INDEX].first)
                            {
                                numberBounds[WEIGHT_INDEX].first
                            }
                            else
                            {
                                newValue
                            }
                    },
                )
            }
        }
    }
}