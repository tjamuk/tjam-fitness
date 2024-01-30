package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle

/**
 * Each exercise works 0 or more muscles.
 *
 * Cannot have nulls or collections (0 or more).
 *
 * So this entity represents this 0 or more relationship between exercises and muscles.
 */
@Entity(
    tableName = "exercises_to_muscles_cross_ref_table",
    foreignKeys = [
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exercise_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    primaryKeys = [
        "exercise_id",
        "muscle"
    ]
)
data class ExerciseWithMuscleEntity(

    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,

    @ColumnInfo(name = "muscle")
    val muscle: Muscle,
)
