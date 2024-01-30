package uk.ac.aber.dcs.cs31620.tjamfitness

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uk.ac.aber.dcs.cs31620.tjamfitness.model.AppViewModel
import uk.ac.aber.dcs.cs31620.tjamfitness.navigation.Screen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.AddExerciseToSessionScreen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.AllExercisesScreen
//import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.AddExerciseToSessionScreenTopLevel
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.AllSessionsScreen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.CreateExerciseScreen
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.OneExerciseScreenTopLevel
import uk.ac.aber.dcs.cs31620.tjamfitness.ui.screens.OneSessionScreen
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

/**
 * Function used for picking an image from gallery.
 *
 * Used in edit/create exercise form for the image of the exercise.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImagePicker(
    imageUri: Uri?,
    onImageUpload: (Uri?) -> Unit
)
{
    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        onImageUpload(uri)
    }

    GlideImage(
        model =
            if (imageUri != null)
                Uri.parse(imageUri.toString())
            else
                Uri.parse(stringResource(R.string.default_image)),
        contentDescription = "image",
        modifier = Modifier.size(84.dp).clip(RoundedCornerShape(15)).clickable {
            imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        },
        contentScale = ContentScale.Crop
    )
}

/**
 * Builds the navigation graph.
 */
@Composable
private fun BuildNavGraph(
    appViewModel: AppViewModel = viewModel()
)
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AllSessions.route //TODO: MAKE TO AllSessions
    )
    {
        composable(Screen.AllSessions.route) {AllSessionsScreen(navController, appViewModel)}

        composable(Screen.AllExercises.route) { AllExercisesScreen(navController, appViewModel) }

        composable(Screen.OneSession.route) {
            OneSessionScreen(
                navController,
                appViewModel,
            )
        }

        composable(Screen.OneExercise.route) { OneExerciseScreenTopLevel(navController, appViewModel) }

        composable(Screen.AddExerciseToSession.route) {AddExerciseToSessionScreen(navController, appViewModel)}

        composable(Screen.SearchExercise.route) {}

        composable(Screen.CreateExercise.route) {CreateExerciseScreen(navController, appViewModel)}
    }
}