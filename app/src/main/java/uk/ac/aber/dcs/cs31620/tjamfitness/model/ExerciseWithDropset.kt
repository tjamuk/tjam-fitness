package uk.ac.aber.dcs.cs31620.tjamfitness.model

import androidx.room.Embedded
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.DropsetEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity

data class ExerciseWithDropset(
    @Embedded
    var exercise: ExerciseEntity,

    @Embedded
    var dropset: DropsetEntity,
)
