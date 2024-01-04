package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import uk.ac.aber.dcs.cs31620.tjamfitness.enumerations.Muscle

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
        "muscle_group"
    ]
)
data class ExerciseWithMuscleEntity(

    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,

    @ColumnInfo(name = "muscle_group")
    val muscle: Muscle,
)
