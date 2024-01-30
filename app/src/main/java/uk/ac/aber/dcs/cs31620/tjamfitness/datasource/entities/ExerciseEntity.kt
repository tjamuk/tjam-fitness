package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An entity representing an exercise.
 *
 * @see DropsetEntity for its dropset set values.
 * @see ExerciseWithMuscleEntity for the muscles that it works.
 */
@Entity(tableName = "exercises_table")
data class ExerciseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var name: String,

    @ColumnInfo(name = "img_path")
    var imgPath: String,

    var sets: Int,

    var reps: Int,

    var weight: Int,

    @ColumnInfo(name = "has_dropset")
    var hasDropset: Boolean
)
