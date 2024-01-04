package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "dropsets_table",
    foreignKeys = [
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exercise_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class DropsetEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,

    @ColumnInfo(name = "first_set")
    var firstSet: Int,

    @ColumnInfo(name = "second_set")
    var secondSet: Int,

    @ColumnInfo(name = "third_set")
    var thirdSet: Int,
)
