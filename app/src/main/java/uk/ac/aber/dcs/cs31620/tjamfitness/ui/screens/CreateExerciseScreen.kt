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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.model.AppViewModel
import uk.ac.aber.dcs.cs31620.tjamfitness.ImagePicker
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.muscles
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.AllAreasChanger
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.DropsetAdder
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.NameField
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.NumbersSection
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.SectionDivider
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.OneExerciseTopAppBar

/**
 * The page representing a form for creating an exercise.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExerciseScreen(
    navController: NavController,
    appViewModel: AppViewModel = viewModel()
)
{
    var name by remember { mutableStateOf("") }
    var selectedMuscles by remember { mutableStateOf<Set<Muscle>>(emptySet()) }

    var isDropsetEnabled by remember { mutableStateOf(false) }
    var selectedSetIndex by remember { mutableStateOf(0) }
    val dropsetValues = remember {
        mutableStateListOf<Int>().apply { addAll(listOf(0,0,0)) }
    }

    var selectedNumIndex by remember { mutableStateOf(0) }
    val numbersValues = remember {
        mutableStateListOf<Int>().apply { addAll(listOf(3, 8, 10)) }
    }

    val defaultImagePath = stringResource(R.string.default_image)
    var imageUri by remember { mutableStateOf<Uri?>(Uri.parse(defaultImagePath)) }

    var newValue: Int

    Scaffold(
        topBar = {
            OneExerciseTopAppBar(
                isCreatingExercise = true,
                isFormComplete = (name.length in NAME_CHAR_MIN..NAME_CHAR_MAX),
                onGoBack = {
                    navController.navigateUp()
                         },
                onSubmitForm = {
                    if (isDropsetEnabled)
                    {
                        appViewModel.insertExercise(
                            exercise = ExerciseEntity(0,name, imageUri.toString(), numbersValues[0], numbersValues[1], numbersValues[2], hasDropset = isDropsetEnabled),
                            firstSet = dropsetValues[0],
                            secondSet = dropsetValues[1],
                            thirdSet = dropsetValues[2],
                            muscles = selectedMuscles.toList(),
                        )
                    }
                    else
                    {
                        appViewModel.insertExercise(
                            exercise = ExerciseEntity(0,name, "", numbersValues[0], numbersValues[1], numbersValues[2], hasDropset = isDropsetEnabled),
                            muscles = selectedMuscles.toList()
                        )
                    }
                    navController.navigateUp()
                },
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