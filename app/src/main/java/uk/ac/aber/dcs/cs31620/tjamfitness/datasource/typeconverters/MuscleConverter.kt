package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.typeconverters

import androidx.room.TypeConverter
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.days

object MuscleConverter {
    @TypeConverter
    @JvmStatic
    fun fromMuscleToString(muscleEnum: Muscle) = muscleEnum.toString()

    @TypeConverter
    @JvmStatic
    fun fromStringToMuscle(muscleString: String) = Muscle.valueOf(muscleString)
}