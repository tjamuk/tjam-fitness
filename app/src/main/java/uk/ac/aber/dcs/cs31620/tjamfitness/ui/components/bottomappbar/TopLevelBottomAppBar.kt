//package uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.bottomappbar
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Checkbox
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import uk.ac.aber.dcs.cs31620.tjamfitness.R
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopLevelBottomAppBar()
//{
//    BottomAppBar(
//        modifier = Modifier.align(Alignment.Center),
//        actions = {
//
//            BottomAppBarItem(
//                icon_painter = painterResource(R.drawable.sessions),
//                icon_desc = "Sessions",
//                title = "Sessions"
//            )
//
//            BottomAppBarItem(
//                icon_painter = painterResource(R.drawable.exercises),
//                icon_desc = "Exercises",
//                title = "Exercises"
//            )
//        }
//    )
//}
//
//private const val HEIGHT = 32
//private const val C0RNER_SIZE = HEIGHT/2
//private const val WIDTH = 64
//
//@Composable
//private fun BottomAppBarItem(
//    icon_painter: Painter,
//    icon_desc: String,
//    title: String,
//    )
//{
//    Column()
//    {
//        Box(
//            modifier = Modifier
//                .size(height = HEIGHT.dp, width = WIDTH.dp)
//                .clip(RoundedCornerShape(C0RNER_SIZE.dp))
//                .background(Color.Red)
//        )
//        {
//            IconButton(
//                content = {
//                    Icon(
//                        painter = icon_painter,
//                        contentDescription = icon_desc,
//                    )
//                },
//                onClick = {},
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//        Text(title)
//    }
//}
//
//@Preview
//@Composable
//fun PreviewEditSessionsTopAppBar()
//{
//    TopLevelBottomAppBar()
//}