package uk.ac.aber.dcs.cs31620.tjamfitness.model

import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.MuscleGroup

private val SET_BOUNDS = Pair(1,10)
private val REP_BOUNDS = Pair(1,30)
private val WEIGHT_BOUNDS = Pair(0,1000)
private val NAME_SIZE_BOUNDS = Pair(4,20)
private const val NUM_DROP_SETS = 3

data class Exercise(
    var id: Int,
    var name: String = "",
    var resourceId: Int?,
    var sets: Int = 3,
    var reps: Int = 8,
    var weight: Int = 10,
    var muscleGroups: Set<MuscleGroup>? = null, //
    var muscles: Set<Muscle>? = null, //
    var dropset: Array<Int>? = null,
)
