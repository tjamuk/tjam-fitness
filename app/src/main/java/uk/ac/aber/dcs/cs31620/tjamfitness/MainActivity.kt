package uk.ac.aber.dcs.cs31620.tjamfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.components.listitem.DayListItem
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.AllSessionsScreen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.OneExerciseScreen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.theme.TJAMFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TJAMFitnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavGraph()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TJAMFitnessTheme {
        Greeting("Android")
    }
}

@Composable
private fun BuildNavGraph()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.OneExercise.route //TODO: MAKE TO AllSessions
    )
    {
        composable(Screen.AllSessions.route) {AllSessionsScreen(navController)}
        composable(Screen.AllExercises.route) {}
        composable(Screen.OneSession.route) {}
        composable(Screen.OneExercise.route) {OneExerciseScreen(navController)}
        composable(Screen.AddExerciseToSession.route) {}
        composable(Screen.SearchExercise.route) {}
    }
}