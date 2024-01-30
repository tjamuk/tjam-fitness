package uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations

/**
 * Enum for muscle groups that can be worked by exercises.
 */
enum class Muscle(val string: String)
{
    ABS(
        string = "Abs",
    ),

    BACK(
        string = "Back",
    ),

    BICEPS(
        string = "Bicep",
    ),

    CHEST(
        string = "Chest",
    ),

    DELTOIDS(
        string = "Deltoids",
    ),

    LEGS(
        string = "Legs",
    ),

    TRICEPS(
        string = "Triceps",
    ),

    FOREARMS(
        string = "Forearms",
    )
}

val muscles = Muscle.values()
val numOfMuscles = muscles.size
val numOfMusclesHalved = numOfMuscles /2