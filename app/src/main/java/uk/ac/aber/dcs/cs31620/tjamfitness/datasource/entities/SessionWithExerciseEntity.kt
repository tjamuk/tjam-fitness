package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day

/**
 * Each session has 0 or more exercises.
 *
 * Cannot have nulls or collections (0 or more).
 *
 * So this entity represents this 0 or more relationship between sessions and exercises.
 */
@Entity(
    tableName = "sessions_to_exercises_cross_ref_table",
    primaryKeys = [
        "exercise_id",
        "session_day",
    ],
    foreignKeys = [
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exercise_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SessionEntity::class,
            parentColumns = arrayOf("day"),
            childColumns = arrayOf("session_day"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
data class SessionWithExerciseEntity(
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,

    @ColumnInfo(name = "session_day")
    val sessionDay: Day,

    @ColumnInfo(name = "order_index")
    var orderIndex: Int,
)