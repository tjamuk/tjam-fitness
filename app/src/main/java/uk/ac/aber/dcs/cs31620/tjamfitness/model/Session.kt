package uk.ac.aber.dcs.cs31620.tjamfitness.model


import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Day

//@Entity(tableName = "sessions")
//data class Session(
//    @PrimaryKey(autoGenerate = false)
//    val day: Day,
//    var exercises: MutableList<Int>,
//    var desc: String,
//    @ColumnInfo(name = "resource-id")
//    var resourceId: Int?,
//    )
//{
//
//}

data class Session(
    val day: Day,
    var desc: String,
)

