package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.FilterTiltShift
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.LooksOne
import androidx.compose.material.icons.outlined.LooksTwo
import androidx.compose.material.icons.outlined.Loop
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.rounded.RepeatOne
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import uk.ac.aber.dcs.cs31620.tjamfitness.R
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.muscles
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.numOfMuscles
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.numOfMusclesHalved
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.SegmentedButtons
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.small.OneExerciseTopAppBar

const val NAME_CHAR_MAX = 25
const val NAME_CHAR_MIN = 2

const val SETS_MAX = 12
const val SETS_MIN = 1

const val REPS_MAX = 25
const val REPS_MIN = 1

val numberBounds: Array<Pair<Int, Int>> = arrayOf(
    Pair(1, 12),
    Pair(1, 25),
    Pair(0, 777),
)

const val WEIGHT_INDEX = 2

val adderValues: Array<Array<Int>> =
    arrayOf(
        arrayOf(1,3,5),
        arrayOf(1,4,10),
        arrayOf(1,5,20)
    )

val units: Array<String> =
    arrayOf(
        "Sets",
        "Reps",
        "kg"
    )

@Composable
fun AdderButtons(
    selectedNumIndex: Int,
    positive: Boolean,
    onChangeSelectedNumValue: (Int) -> Unit,
)
{
    Column()
    {
        for (value in adderValues[selectedNumIndex])
        {
            OutlinedButton(
                onClick = {
                    onChangeSelectedNumValue(
                        if (positive)
                            value
                        else
                            -value
                    )
                },
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp)

            )
            {
                Text(
                    text =
                        if (positive)
                            "+$value"
                        else
                            "−$value",
                    fontSize = 20.sp,
                    softWrap = false,
                    overflow = TextOverflow.Visible,
                )
            }
        }
    }

}

@Composable
fun ChangeNumbers(
    selectedNumValue: Int,
    selectedNumIndex: Int,
    onChangeSelectedNumValue: (Int) -> Unit,
)
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {
        AdderButtons(
            selectedNumIndex = selectedNumIndex,
            positive = false,
            onChangeSelectedNumValue = onChangeSelectedNumValue,
        )

        Text(
            text = "${selectedNumValue}\n${units[selectedNumIndex]}",
            textAlign = TextAlign.Center
        )

        AdderButtons(
            selectedNumIndex = selectedNumIndex,
            positive = true,
            onChangeSelectedNumValue = onChangeSelectedNumValue,
        )
    }
}

@Composable
fun sectionDivider(
    imageVector: ImageVector,
    title: String,
)
{
    ConstraintLayout {
        val (divider, icon, text) = createRefs()

        Divider(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .constrainAs(divider) {
                    centerHorizontallyTo(parent)
                }
        )
        Icon(
            imageVector,
            title,
            Modifier
                .padding(start = 10.dp)
                .constrainAs(icon) {
                    top.linkTo(divider.bottom)
                    start.linkTo(divider.start)
                },
            tint = DividerDefaults.color

        )
        Text(
            title,
            Modifier
                .constrainAs(text) {
//                    top.linkTo(divider.bottom)
                    start.linkTo(icon.end)
                    centerVerticallyTo(icon)
                }
                .padding(start = 3.dp),
            color = DividerDefaults.color
        )
    }
}

@Composable
fun IconAndText(
    imageVector: ImageVector,
    text: String,
)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector,
            text,
        )
        Text(text)
    }
}

@Composable
fun SingleAreaChanger(
    isCheckboxChecked: Boolean,
    onCheckboxChanged: (Boolean, Int) -> Unit,
    muscle: Muscle
)
{
//    var isDropdownEnabled = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        Checkbox(
            checked = isCheckboxChecked,
            onCheckedChange = { isChecked ->
                onCheckboxChanged(isChecked, muscle.ordinal)
            }
        )
        Text(muscle.string)
    }

}

@Composable
fun DropsetAdder(
    isCheckboxChecked: Boolean,
    onCheckboxChanged: (Boolean) -> Unit,
    selectedSetIndex: Int,
    onChangeSelectedSetIndex: (Int) -> Unit,
    getSetValue: (Int) -> Int,
    onChangeSelectedSetValue: (Int) -> Unit,
)
{
    var isDropdownEnabled = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize()
    ) {
        Checkbox(
            checked = isCheckboxChecked,
            onCheckedChange = { isChecked ->
                onCheckboxChanged(isChecked)
            }
        )
        Text("Add Dropset?")
        IconButton(
            onClick = {
                isDropdownEnabled.value = !(isDropdownEnabled.value)
            },
            content = {
                Icon(
                    imageVector =
                        if (isDropdownEnabled.value)
                            Icons.Filled.KeyboardArrowDown
                        else
                            Icons.Filled.KeyboardArrowRight,
                    contentDescription = "show muscles",
                )
            },
        )
    }
    if (isDropdownEnabled.value)
    {
        NumbersSection(
            selectedNumIndex = selectedSetIndex,
            onChangeSelectedNumIndex = onChangeSelectedSetIndex,
            getNumValue = getSetValue,
            onChangeSelectedNumValue = onChangeSelectedSetValue,
            isNumbersSection = false,
        )
    }
}

@Composable
fun AllAreasChanger(
    onCheckboxChanged: (Boolean, Int) -> Unit,
    getCheckboxValue: (Int) -> Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    )
    {
        Column()
        {
            for (muscleGroupIndex in 0..(numOfMusclesHalved - 1)) {
                SingleAreaChanger(
                    isCheckboxChecked = getCheckboxValue(muscleGroupIndex),
                    onCheckboxChanged = onCheckboxChanged,
                    muscle = muscles[muscleGroupIndex]
                )
            }
        }
        Column()
        {
            for (muscleGroupIndex in numOfMusclesHalved..(numOfMuscles - 1)) {
                SingleAreaChanger(
                    isCheckboxChecked = getCheckboxValue(muscleGroupIndex),
                    onCheckboxChanged = onCheckboxChanged,
                    muscle = muscles[muscleGroupIndex]
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(
    name: String,
    onValueChange: (String) -> Unit
)
{
    val isNameBadLength: Boolean = (name.length !in NAME_CHAR_MIN..NAME_CHAR_MAX)

    OutlinedTextField(
        label = { Text("Name") },
        value = name,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text("Enter a name") },
        isError = isNameBadLength,
        supportingText = {
            if (isNameBadLength)
            {
                Text("At least ${NAME_CHAR_MIN} and at most ${NAME_CHAR_MAX} characters")
            }
        },
        trailingIcon = {
            IconButton(
                content = { Icon(Icons.Outlined.Clear, "clear text field") },
                onClick = {onValueChange("")}
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    )
}

@Composable
fun NumbersSection(
    selectedNumIndex: Int,
    onChangeSelectedNumIndex: (Int) -> Unit,
    getNumValue: (Int) -> Int,
    onChangeSelectedNumValue: (Int) -> Unit,
    isNumbersSection: Boolean,
)
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (isNumbersSection)
        {
            IconAndText(Icons.Outlined.Loop,"${getNumValue(0)} Sets")
            IconAndText(Icons.Rounded.RepeatOne, "${getNumValue(1)} Reps")
            IconAndText(Icons.Outlined.Scale, "${getNumValue(2)}kg")
        }
        else
        {
            IconAndText(Icons.Outlined.LooksOne,"${getNumValue(0)}kg")
            IconAndText(Icons.Outlined.LooksTwo, "${getNumValue(1)}kg")
            IconAndText(Icons.Outlined.Looks3, "${getNumValue(2)}kg")
        }
    }

    SegmentedButtons(
        items =
            if (isNumbersSection)
                listOf("Sets","Reps", "Weight")
            else
                listOf("Set 1", "Set 2", "Set 3"),
        onItemSelection = onChangeSelectedNumIndex,
        horizontalPadding = 10.dp,
    )

    ChangeNumbers(
        selectedNumValue = getNumValue(selectedNumIndex),
        selectedNumIndex =
            if (isNumbersSection)
                selectedNumIndex
            else
                WEIGHT_INDEX,
        onChangeSelectedNumValue = onChangeSelectedNumValue,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneExerciseScreen(
    navController: NavController? = null,
    isCreatingExercise: Boolean = true //this page can be either used for creating exercises or editting them, to tell which this boolean is used.
)
{
    var name by remember { mutableStateOf("") }
    var selectedAreas by remember { mutableStateOf<Set<Int>>(emptySet()) }

    var isDropsetEnabled by remember { mutableStateOf(false) }
    var selectedSetIndex by remember { mutableStateOf(0) }
    val dropsetValues = remember {
        mutableStateListOf<Int>().apply { addAll(listOf(0,0,0)) }
    }

    var selectedNumIndex by remember { mutableStateOf(0) }
    val numbersValues = remember {
        mutableStateListOf<Int>().apply { addAll(listOf(3, 8, 10)) }
    }

    var newValue: Int

    Scaffold(
        topBar = {
            OneExerciseTopAppBar(
                isCreatingExercise,
                (name.length in NAME_CHAR_MIN..NAME_CHAR_MAX)
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
            sectionDivider(imageVector = Icons.Outlined.Info, title = "Basic Information")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.full_body_fixed),
                    contentDescription = "full-body",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(84.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                NameField(
                    name = name,
                    onValueChange = {name = it}
                )
            }

            sectionDivider(imageVector = Icons.Outlined.Numbers, title = "Numbers")

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

            sectionDivider(imageVector = Icons.Outlined.FilterTiltShift, title = "Muscle Groups")

            AllAreasChanger(
                onCheckboxChanged = { isChecked, muscleGroupOrdinal ->
                    selectedAreas =
                        if (isChecked)
                            selectedAreas.plus(muscleGroupOrdinal)
                        else
                            selectedAreas.minus(muscleGroupOrdinal)
                },
                getCheckboxValue = { muscleGroupOrdinal ->
                    selectedAreas.contains(muscleGroupOrdinal)
                },
            )

            sectionDivider(imageVector = Icons.Filled.TrendingDown, title = "Dropset")

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

//@Preview
//@Composable
//fun previewSectionDivider()
//{
//    sectionDivider(imageVector = Icons.Outlined.Info, title = "Basic Information")
//}

@Preview
@Composable
fun previewOneExerciseScreen()
{
    OneExerciseScreen()
}

@Preview
@Composable
fun preview()
{
    val state = rememberLazyGridState()
    val list = listOf("1", "2", "3", "4")

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = state
    )
    {
        items(list) {item ->
            Text(item)
        }
    }
}

//@Preview
//@Composable
//
//fun previewNumberInfo()
//{
//    IconAndText(
//        Icons.Outlined.RepeatOne,
//        "3 Sets"
//    )
//}

//@Preview
//@Composable
//
//fun previewAdderButton()
//{
//    AdderButtons(
//        index = 0,
//        false,
//        onClick = {_,_ -> }
//    )
//}

//@Preview
//@Composable
//
//fun previewChangeNumbers()
//{
//    ChangeNumbers(
//        value = 170,
//        index = 0,
//        onClick = {_,_ -> }
//    )
//}

//@Preview
//@Composable
//fun previewSingleArea()
//{
//    SingleArea(
//        isCheckboxEnabled = false,
//        onCheckboxChanged = {},
//        muscleGroup = MuscleGroup.ABS
//    )
//}