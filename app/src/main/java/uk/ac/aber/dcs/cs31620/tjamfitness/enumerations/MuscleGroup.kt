package uk.ac.aber.dcs.cs31620.tjamfitness.enumerations

//enum class MuscleGroup(val string: String, val muscles: Set<Muscle>)
//{
//    ABS(
//        string = "Abs",
//        muscles = setOf(
//            Muscle.LOWER_ABS,
//            Muscle.MIDDLE_ABS,
//            Muscle.UPPER_ABS,
//        )
//    ),
//
//    BACK(
//        string = "Back",
//        muscles = setOf(
//            Muscle.ERECTOR_SPINAE,
//            Muscle.LATS,
//            Muscle.RHOMBOIDS,
//            Muscle.TRAPS
//        )
//    ),
//
//    BICEPS(
//        string = "Bicep",
//        muscles = setOf(
//            Muscle.BICEPS_SHORT_HEAD,
//            Muscle.BICEPS_LONG_HEAD,
//        )
//    ),
//
//    CHEST(
//        string = "Abs",
//        muscles = setOf(
//            Muscle.LOWER_CHEST,
//            Muscle.MIDDLE_CHEST,
//            Muscle.UPPER_CHEST,
//        )
//    ),
//
//    DELTOIDS(
//        string = "Abs",
//        muscles = setOf(
//            Muscle.FRONT_DELTS,
//            Muscle.SIDE_DELTS,
//            Muscle.REAR_DELTS,
//        )
//    ),
//
//    LEGS(
//        string = "Abs",
//        muscles = setOf(
//            Muscle.CALVES,
//            Muscle.GLUTES,
//            Muscle.HAMSTRINGS,
//            Muscle.QUADS
//        )
//    ),
//
//    TRICEPS(
//        string = "Abs",
//        muscles = setOf(
//            Muscle.TRICEPS_LATERAL_HEAD,
//            Muscle.TRICEPS_LONG_HEAD,
//            Muscle.TRICEPS_MEDIAL_HEAD,
//        )
//    )
//}

enum class MuscleGroup(val string: String)
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

val muscleGroups = MuscleGroup.values()
val numOfMuscleGroups = muscleGroups.size
val numOfMuscleGroupsHalved = numOfMuscleGroups/2



//val MUSCLE_GROUP_MUSCLES = mapOf<MuscleGroup,Set<Muscle>>(
//    MuscleGroup.ABS to setOf(
//        Muscle.LOWER_ABS,
//        Muscle.MIDDLE_ABS,
//        Muscle.UPPER_ABS,
//    ),
//
//    MuscleGroup.BACK to setOf(
//        Muscle.ERECTOR_SPINAE,
//        Muscle.LATS,
//        Muscle.RHOMBOIDS,
//        Muscle.TRAPS
//    ),
//
//    MuscleGroup.BICEPS to setOf(
//        Muscle.BICEPS_SHORT_HEAD,
//        Muscle.BICEPS_LONG_HEAD,
//    ),
//
//    MuscleGroup.CHEST to setOf(
//        Muscle.LOWER_CHEST,
//        Muscle.MIDDLE_CHEST,
//        Muscle.UPPER_CHEST,
//    ),
//
//    MuscleGroup.DELTOIDS to setOf(
//        Muscle.FRONT_DELTS,
//        Muscle.SIDE_DELTS,
//        Muscle.REAR_DELTS,
//    ),
//
//    MuscleGroup.LEGS to setOf(
//        Muscle.CALVES,
//        Muscle.GLUTES,
//        Muscle.HAMSTRINGS,
//        Muscle.QUADS
//    ),
//
//    MuscleGroup.TRICEPS to setOf(
//        Muscle.TRICEPS_LATERAL_HEAD,
//        Muscle.TRICEPS_LONG_HEAD,
//        Muscle.TRICEPS_MEDIAL_HEAD,
//    ),
//)