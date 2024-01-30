package uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations

import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionEntity

/**
 * Enum for days of the week
 */
enum class Day(
    val string: String,
)
{
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday"),
}

val days = Day.values()
