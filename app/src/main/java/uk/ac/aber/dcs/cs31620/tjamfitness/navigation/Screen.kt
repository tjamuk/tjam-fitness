package uk.ac.aber.dcs.cs31620.tjamfitness.navigation

sealed class Screen(val route: String) {
    object AllSessions : Screen("all-sessions")
    object AllExercises : Screen("all-exercises")

    object OneSession : Screen("one-session")
    object OneExercise : Screen("one-exercise")

    object AddExerciseToSession : Screen("add-exercise-to-session")

    object SearchExercise : Screen("search-exercise")
}

/**
 * List of screens provided as a convenience.
 */
val screens = listOf(
    Screen.AllSessions,
    Screen.AllExercises,
    Screen.OneSession,
    Screen.OneExercise,
    Screen.AddExerciseToSession,
    Screen.SearchExercise,
)

/**
 * List of top-level screens provided as a convenience.
 */
val topLevelScreens = listOf(
    Screen.AllSessions,
    Screen.AllExercises,
)
