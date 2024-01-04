package uk.ac.aber.dcs.cs31620.tjamfitness.enumerations

import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionEntity

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