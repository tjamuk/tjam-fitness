package uk.ac.aber.dcs.cs31620.tjamfitness.model

import androidx.room.ColumnInfo
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day

data class SessionInfo(

    @ColumnInfo("description")
    val description: String,

    @ColumnInfo("img_path")
    var imgPath: String,
)
