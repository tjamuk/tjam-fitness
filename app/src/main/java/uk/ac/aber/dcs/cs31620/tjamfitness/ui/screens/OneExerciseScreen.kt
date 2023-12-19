package uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.FilterTiltShift
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Loop
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.RepeatOne
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.rounded.RepeatOne
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.other.SegmentedButtons
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.topappbar.small.OneExerciseTopAppBar

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
    index: Int,
    positive: Boolean,
    onClick: (Int, Int) -> Unit
)
{
    Column()
    {
        for (value in adderValues[index])
        {
            OutlinedButton(
                onClick = {
                    onClick(
                        index,
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
    value: Int,
    index: Int,
    onClick: (Int, Int) -> Unit
)
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {
        AdderButtons(
            index = index,
            positive = false,
            onClick = onClick,
        )

        Text(
            text = "${value}\n${units[index]}",
            textAlign = TextAlign.Center
        )
        AdderButtons(
            index = index,
            positive = true,
            onClick = onClick,
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
                    top.linkTo(divider.bottom)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneExerciseScreen(
    navController: NavController? = null,
    isCreatingExercise: Boolean = true //this page can be either used for creating exercises or editting them, to tell which this boolean is used.

)
{
    Scaffold(
        topBar = {
            OneExerciseTopAppBar(isCreatingExercise)
        },
    )
    { innerPadding ->
        Column(
            Modifier.padding(innerPadding)
        )
        {
            sectionDivider(imageVector = Icons.Outlined.Info, title = "Basic Information")

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.full_body_fixed),
                    contentDescription = "full-body",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(84.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                OutlinedTextField(
                    label = { Text("Name") },
                    value = "test",
                    onValueChange = {},
                    trailingIcon = {
                        IconButton(
                            content = { Icon(Icons.Outlined.Clear, "clear text field") },
                            onClick = {}
                        )
                    }
                )
            }

            sectionDivider(imageVector = Icons.Outlined.Numbers, title = "Numbers")

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconAndText(Icons.Outlined.Loop, "3 Sets")
                IconAndText(Icons.Rounded.RepeatOne, "8 reps")
                IconAndText(Icons.Outlined.Scale, "10kg")
            }

//            SegmentedButtons(
//                items = listOf(
//                    "Sets",
//                    "Reps",
//                    "Weight"
//                ),
//                onItemSelection = {},
//                horizontalPadding = 10.dp
//            )

            ChangeNumbers(
                value = 170,
                index = 0,
                onClick = {_,_ ->}
            )

            sectionDivider(imageVector = Icons.Outlined.FilterTiltShift, title = "Area")
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