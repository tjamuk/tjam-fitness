package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity

/**
 * The direct access object for all methods related to ExerciseEntity
 */
@Dao
interface ExerciseDao {
    @Insert
    fun insertSingleExercise(exercise: ExerciseEntity)

    @Update
    fun updateSingleExercise(exercise: ExerciseEntity)

    @Delete
    fun deleteSingleExercise(exercise: ExerciseEntity)

    @Query("DELETE FROM exercises_table WHERE id IN (:exerciseIds)")
    fun deleteMultipleExercises(exerciseIds: List<Int>)

    @Query("SELECT id FROM exercises_table WHERE name == :name AND sets == :sets AND reps == :reps AND weight == :weight AND has_dropset == :hasDropset")
    fun getSingleExerciseId(name: String, sets: Int, reps: Int, weight: Int, hasDropset: Boolean): LiveData<Int>

    @Query("SELECT id FROM exercises_table WHERE name == :name AND sets == :sets AND reps == :reps AND weight = :weight AND has_dropset = :hasDropset")
    fun getSingleExerciseIdInt(name: String, sets: Int, reps: Int, weight: Int, hasDropset: Boolean): Int

    @Query(
        """
            SELECT * 
            FROM exercises_table 
            WHERE id IN (:exerciseIdList)
        """
    )
    suspend fun getMultipleExercisesFromId(exerciseIdList: List<Int>): List<ExerciseEntity>
}