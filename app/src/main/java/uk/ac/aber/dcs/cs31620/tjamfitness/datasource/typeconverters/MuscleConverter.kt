package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.typeconverters

import androidx.room.TypeConverter
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.days

/**
 * A type converter that converts to and from enum Muscle
 *
 * @see Muscle
 */
object MuscleConverter {
    @TypeConverter
    @JvmStatic
    fun fromMuscleToString(muscleEnum: Muscle) = muscleEnum.toString()

    @TypeConverter
    @JvmStatic
    fun fromStringToMuscle(muscleString: String) = Muscle.valueOf(muscleString)
}