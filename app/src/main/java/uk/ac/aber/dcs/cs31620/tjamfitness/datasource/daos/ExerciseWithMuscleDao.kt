package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.Query
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseWithMuscleEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle

/**
 * The direct access object for all methods somewhat related to joining both exercises_table and exercises_to_muscles_cross_ref_table.
 *
 * Each exercise works 0 or more muscles. This dao is to basically to get these muscles for 1 or more exercises.
 */
@Dao
interface ExerciseWithMuscleDao {
    @Insert
    fun insertExercisesToMuscleGroups(exercisesToMuscles: List<ExerciseWithMuscleEntity>)
    @Insert
    fun insertExerciseToMuscleGroup(exerciseToMuscle: ExerciseWithMuscleEntity)

    @Query("SELECT * FROM exercises_to_muscles_cross_ref_table JOIN exercises_table ON exercise_id == id")
    fun getExerciseToMuscleMapping() : LiveData<Map<

            @MapColumn(columnName = "id")
            Int,

            List<
                    @MapColumn(columnName = "muscle")
                    Muscle
                    >
            >>

    @Query("SELECT muscle FROM exercises_to_muscles_cross_ref_table JOIN exercises_table ON exercise_id == id WHERE id == :exerciseId")
    fun getExerciseMuscles(exerciseId: Int): LiveData<List<Muscle>>

    @Query("SELECT muscle FROM exercises_to_muscles_cross_ref_table JOIN exercises_table ON exercise_id == id WHERE id == :exerciseId")
    fun getExerciseMusclesTest(exerciseId: Int): LiveData<List<Muscle>>

    @Query(
        """
            DELETE FROM exercises_to_muscles_cross_ref_table
            WHERE exercise_id == :exerciseId AND muscle IN (:muscles)
        """
    )
    fun deleteSomeExerciseMuscles(exerciseId: Int, muscles: List<Muscle>)

    @Query(
        """
            INSERT INTO exercises_to_muscles_cross_ref_table
            VALUES (:exerciseId, :muscles)
        """
    )
    fun insertMusclesForExercise(exerciseId: Int, muscles: List<Muscle>)

    @Query(
        """
            SELECT muscle
            FROM exercises_to_muscles_cross_ref_table
            WHERE exercise_id IN (:exerciseIdList)
        """
    )
    fun getMusclesFromExercises(exerciseIdList: List<Int>): List<Muscle>

}