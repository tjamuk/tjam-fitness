package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Day

@Entity(tableName = "sessions_table")
data class SessionEntity(

    @PrimaryKey(autoGenerate = false)
    val day: Day,

    var description: String = "Rest",

    @ColumnInfo(name = "img_path")
    var imgPath: String,
)
