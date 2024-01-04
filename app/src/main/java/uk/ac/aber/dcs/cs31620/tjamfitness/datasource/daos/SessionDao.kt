package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionEntity

//import uk.ac.aber.dcs.cs31620.tjamfitness.model.Exercise
//
@Dao
interface SessionDao {
    @Insert
    fun insertSingleSession(exercise: ExerciseEntity)

    @Update
    fun updateSingleSession(exercise: ExerciseEntity)

    @Update
    fun updateMultipleSessions(sessions: List<SessionEntity>)

}