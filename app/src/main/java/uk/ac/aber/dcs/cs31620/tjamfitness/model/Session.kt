package uk.ac.aber.dcs.cs31620.tjamfitness.model

import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Day

data class Session(
    val day: Day,
    var exercises: MutableList<Int>,
    var desc: String,
    var resourceId: Int?,
    )
{

}
