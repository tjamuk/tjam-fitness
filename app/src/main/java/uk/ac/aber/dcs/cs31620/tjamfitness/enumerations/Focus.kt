package uk.ac.aber.dcs.cs31620.tjamfitness.enumerations
//
//enum class Focus(
//    val string: String,
//    val optional_muscle_groups: Set<Muscle>,
//    val min_num_of_optional_muscle_groups: Int,
//    val req_muscle_groups: Set<Muscle>? = null,
//)
//{
//    //Just singular areas
//
////    ABS(
////        string = MuscleGroup.ABS.string,
////        muscle_groups = setOf(
////            MuscleGroup.ABS
////        ),
////        min_num_of_muscle_groups = 1
////    ),
////
////    BACK(
////        string = MuscleGroup.BACK.string,
////        muscle_groups = setOf(
////            MuscleGroup.BACK
////        ),
////        min_num_of_muscle_groups = 1
////    ),
////
////    BICEPS(
////        string = MuscleGroup.BICEPS.string,
////        muscle_groups = setOf(
////            MuscleGroup.BICEPS
////        ),
////        min_num_of_muscle_groups = 1
////    ),
////
////    CHEST(
////        string = MuscleGroup.CHEST.string,
////        muscle_groups = setOf(
////            MuscleGroup.CHEST
////        ),
////        min_num_of_muscle_groups = 1
////    ),
////
////    DELTOIDS(
////        string = MuscleGroup.DELTOIDS.string,
////        muscle_groups = setOf(
////            MuscleGroup.DELTOIDS
////        ),
////        min_num_of_muscle_groups = 1
////    ),
////
////    LEGS(
////        string = MuscleGroup.LEGS.string,
////        muscle_groups = setOf(
////            MuscleGroup.LEGS
////        ),
////        min_num_of_muscle_groups = 1
////    ),
////
////    TRICEPS(
////        string = MuscleGroup.TRICEPS.string,
////        muscle_groups = setOf(
////            MuscleGroup.TRICEPS
////        ),
////        min_num_of_muscle_groups = 1
////    ),
//
//    //Common com
//
//    UPPER_BODY(
//        string = "Upper Body",
//        optional_muscle_groups = setOf(
//            Muscle.BACK,
//            Muscle.BICEPS,
//            Muscle.CHEST,
//            Muscle.DELTOIDS,
//            Muscle.TRICEPS,
//        ),
//        min_num_of_optional_muscle_groups = 4
//    ),
//
//    ARMS(
//        string = "Arms",
//        optional_muscle_groups = setOf(
//            Muscle.BICEPS,
//            Muscle.TRICEPS,
//        ),
//        min_num_of_optional_muscle_groups = 2
//    ),
//
//    FULL_BODY(
//        string = "Full Body",
//        optional_muscle_groups = setOf(
//            Muscle.BACK,
//            Muscle.BICEPS,
//            Muscle.CHEST,
//            Muscle.DELTOIDS,
//            Muscle.TRICEPS,
//        ),
//        min_num_of_optional_muscle_groups = 2,
//        req_muscle_groups = setOf(
//            Muscle.LEGS
//        )
//    ),
//}
//
//
//
//val specialFocuses = listOf<SpecialFocus>(
//
//    SpecialFocus(
//        title = "Full-Body",
//        optionalMuscles = setOf(
//            Muscle.ABS,
//            Muscle.BACK,
//            Muscle.BICEPS,
//            Muscle.CHEST,
//            Muscle.DELTOIDS,
//            Muscle.TRICEPS,
//        ),
//        minNumOfMuscleGroups = 4,
//        reqMuscles = setOf(
//            Muscle.LEGS
//        )
//    ),
//
//    SpecialFocus(
//        title = "Upper-Body",
//        optionalMuscles = setOf(
//            Muscle.ABS,
//            Muscle.BACK,
//            Muscle.BICEPS,
//            Muscle.CHEST,
//            Muscle.DELTOIDS,
//            Muscle.TRICEPS,
//        ),
//        minNumOfMuscleGroups = 4,
//    ),
//
//    SpecialFocus(
//        title = "Arms",
//        optionalMuscles = setOf(
//            Muscle.BICEPS,
//            Muscle.TRICEPS,
//        ),
//        minNumOfMuscleGroups = 2,
//    ),
//)
//
//data class SpecialFocus(
//    val title: String,
//    private val optionalMuscles: Set<Muscle>,
//    private val minNumOfMuscleGroups: Int,
//    private val reqMuscles: Set<Muscle>? = null
//)
//{
//    fun doMuscleGroupsMatchFocus(
//        muscles: Set<Muscle>
//    ): Boolean
//    {
//        if (muscles.size < minNumOfMuscleGroups)
//        {
//            return false
//        }
//
//        if (reqMuscles != null)
//        {
//            if ((reqMuscles intersect muscles).isEmpty())
//            {
//                return false
//            }
//        }
//
//        if (
//            (optionalMuscles intersect muscles).size
//            <
//            minNumOfMuscleGroups - (reqMuscles?.size?: 0)
//        )
//        {
//            return false
//        }
//
//        return true
//    }
//}
//
//fun muscleGroupsToFocus(
//    muscles: Set<Muscle>
//): String
//{
//    for (focus in specialFocuses)
//    {
//        if (focus.doMuscleGroupsMatchFocus(muscles)) {
//            return focus.title
//        }
//    }
//
//    val focusString = StringBuilder()
//
//    muscles.forEachIndexed{ index, muscleGroup ->
//
//        focusString.append(muscleGroup.string)
//
//        if (index == 2)
//        {
//            return focusString.toString()
//        }
//
//        focusString.append(", ")
//    }
//
//    return focusString.dropLast(2).toString()
//}