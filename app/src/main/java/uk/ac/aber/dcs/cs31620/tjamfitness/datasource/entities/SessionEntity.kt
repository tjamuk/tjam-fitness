package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day

/**
 * An entity representing a session.
 *
 * @see SessionWithExerciseEntity for a session's exercises.
 */
@Entity(tableName = "sessions_table")
data class SessionEntity(

    @PrimaryKey(autoGenerate = false)
    val day: Day,

    var description: String = "Rest",

    @ColumnInfo(name = "img_path")
    var imgPath: String,
)
