//package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.Query
//import androidx.room.Update
//import uk.ac.aber.dcs.cs31620.tjamfitness.model.Exercise
//
//@Dao
//interface ExerciseDao {
//    @Insert
//    fun insertSingleExercise(exercise: Exercise)
//
//    @Update
//    fun updateSingleExercise(exercise: Exercise)
//
//    @Delete
//    fun deleteSingleExercise(exercise: Exercise)
//
//    @Query("DELETE FROM exercises")
//    fun deleteAllExercises()
//
//    @Query("SELECT * FROM exercises")
//    fun getAllExercises()
//
//    @Query("SELECT * FROM exercises WHERE ")
//    fun getAllExercises()
//}