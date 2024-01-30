package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.typeconverters

import androidx.room.TypeConverter
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.days

/**
 * A type converter that converts to and from enum Day
 *
 * @see Day
 */
object DayConverter {
    @TypeConverter
    @JvmStatic
    fun fromDayToInt(dayEnum: Day) = dayEnum.ordinal

    @TypeConverter
    @JvmStatic
    fun fromIntToDay(dayInt: Int) = days[dayInt]
}