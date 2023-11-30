package uk.ac.aber.dcs.cs31620.tjamfitness.enumerations

enum class Focus(
    val string: String,
    val optional_muscle_groups: Set<MuscleGroup>,
    val min_num_of_optional_muscle_groups: Int,
    val req_muscle_groups: Set<MuscleGroup>? = null,
)
{
    //Just singular areas

//    ABS(
//        string = MuscleGroup.ABS.string,
//        muscle_groups = setOf(
//            MuscleGroup.ABS
//        ),
//        min_num_of_muscle_groups = 1
//    ),
//
//    BACK(
//        string = MuscleGroup.BACK.string,
//        muscle_groups = setOf(
//            MuscleGroup.BACK
//        ),
//        min_num_of_muscle_groups = 1
//    ),
//
//    BICEPS(
//        string = MuscleGroup.BICEPS.string,
//        muscle_groups = setOf(
//            MuscleGroup.BICEPS
//        ),
//        min_num_of_muscle_groups = 1
//    ),
//
//    CHEST(
//        string = MuscleGroup.CHEST.string,
//        muscle_groups = setOf(
//            MuscleGroup.CHEST
//        ),
//        min_num_of_muscle_groups = 1
//    ),
//
//    DELTOIDS(
//        string = MuscleGroup.DELTOIDS.string,
//        muscle_groups = setOf(
//            MuscleGroup.DELTOIDS
//        ),
//        min_num_of_muscle_groups = 1
//    ),
//
//    LEGS(
//        string = MuscleGroup.LEGS.string,
//        muscle_groups = setOf(
//            MuscleGroup.LEGS
//        ),
//        min_num_of_muscle_groups = 1
//    ),
//
//    TRICEPS(
//        string = MuscleGroup.TRICEPS.string,
//        muscle_groups = setOf(
//            MuscleGroup.TRICEPS
//        ),
//        min_num_of_muscle_groups = 1
//    ),

    //Common com

    UPPER_BODY(
        string = "Upper Body",
        optional_muscle_groups = setOf(
            MuscleGroup.BACK,
            MuscleGroup.BICEPS,
            MuscleGroup.CHEST,
            MuscleGroup.DELTOIDS,
            MuscleGroup.TRICEPS,
        ),
        min_num_of_optional_muscle_groups = 4
    ),

    ARMS(
        string = "Arms",
        optional_muscle_groups = setOf(
            MuscleGroup.BICEPS,
            MuscleGroup.TRICEPS,
        ),
        min_num_of_optional_muscle_groups = 2
    ),

    FULL_BODY(
        string = "Full Body",
        optional_muscle_groups = setOf(
            MuscleGroup.BACK,
            MuscleGroup.BICEPS,
            MuscleGroup.CHEST,
            MuscleGroup.DELTOIDS,
            MuscleGroup.TRICEPS,
        ),
        min_num_of_optional_muscle_groups = 2,
        req_muscle_groups = setOf(
            MuscleGroup.LEGS
        )
    ),
}



val specialFocuses = listOf<SpecialFocus>(

    SpecialFocus(
        title = "Full-Body",
        optionalMuscleGroups = setOf(
            MuscleGroup.ABS,
            MuscleGroup.BACK,
            MuscleGroup.BICEPS,
            MuscleGroup.CHEST,
            MuscleGroup.DELTOIDS,
            MuscleGroup.TRICEPS,
        ),
        minNumOfMuscleGroups = 4,
        reqMuscleGroups = setOf(
            MuscleGroup.LEGS
        )
    ),

    SpecialFocus(
        title = "Upper-Body",
        optionalMuscleGroups = setOf(
            MuscleGroup.ABS,
            MuscleGroup.BACK,
            MuscleGroup.BICEPS,
            MuscleGroup.CHEST,
            MuscleGroup.DELTOIDS,
            MuscleGroup.TRICEPS,
        ),
        minNumOfMuscleGroups = 4,
    ),

    SpecialFocus(
        title = "Arms",
        optionalMuscleGroups = setOf(
            MuscleGroup.BICEPS,
            MuscleGroup.TRICEPS,
        ),
        minNumOfMuscleGroups = 2,
    ),
)

data class SpecialFocus(
    val title: String,
    private val optionalMuscleGroups: Set<MuscleGroup>,
    private val minNumOfMuscleGroups: Int,
    private val reqMuscleGroups: Set<MuscleGroup>? = null
)
{
    fun doMuscleGroupsMatchFocus(
        muscleGroups: Set<MuscleGroup>
    ): Boolean
    {
        if (muscleGroups.size < minNumOfMuscleGroups)
        {
            return false
        }

        if (reqMuscleGroups != null)
        {
            if ((reqMuscleGroups intersect muscleGroups).isEmpty())
            {
                return false
            }
        }

        if (
            (optionalMuscleGroups intersect muscleGroups).size
            <
            minNumOfMuscleGroups - (reqMuscleGroups?.size?: 0)
        )
        {
            return false
        }

        return true
    }
}

fun muscleGroupsToFocus(
    muscleGroups: Set<MuscleGroup>
): String
{
    for (focus in specialFocuses)
    {
        if (focus.doMuscleGroupsMatchFocus(muscleGroups)) {
            return focus.title
        }
    }

    if (muscleGroups.size > 3)
    {
        return
    }
}