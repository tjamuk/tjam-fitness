package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity

//import uk.ac.aber.dcs.cs31620.tjamfitness.model.Exercise
//
@Dao
interface ExerciseDao {
    @Insert
    fun insertSingleExercise(exercise: ExerciseEntity)

    @Update
    fun updateSingleExercise(exercise: ExerciseEntity)

    @Delete
    fun deleteSingleExercise(exercise: ExerciseEntity)

    @Query("DELETE FROM exercises_table")
    fun deleteAllExercises()

    @Delete
    fun deleteMultipleExercises(exercises: List<ExerciseEntity>)

}