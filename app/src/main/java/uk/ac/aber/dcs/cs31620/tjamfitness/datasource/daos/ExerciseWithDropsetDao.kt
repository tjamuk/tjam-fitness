package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.model.ExerciseWithDropset

/**
 * The direct access object for all methods somewhat related to joining both exercises_table and dropsets_table and returning in some way or another object(s) of data class ExerciseWithDropset
 */
@Dao
interface ExerciseWithDropsetDao {

    @Query("SELECT * FROM exercises_table LEFT JOIN dropsets_table ON id == exercise_id")
    fun getAllExercisesWithDropsets() : LiveData<List<ExerciseWithDropset>>

    @Query("SELECT * FROM exercises_table LEFT JOIN dropsets_table ON id == exercise_id")
    fun getAllExercisesWithDropsetsList() : List<ExerciseWithDropset>

    @Query("SELECT * FROM exercises_table LEFT JOIN dropsets_table ON id == exercise_id WHERE name LIKE (:name) ORDER BY name")
    fun getAllExercisesWithDropsetsByName(name: String): LiveData<List<ExerciseWithDropset>>

    @Query("SELECT * FROM exercises_table LEFT JOIN dropsets_table ON id == exercise_id WHERE name LIKE (:name) AND id NOT IN (:exercisesToExclude) ORDER BY name")
    fun getAllExercisesWithDropsetsByNameWithoutSessionExercises(name: String, exercisesToExclude: List<Int>): LiveData<List<ExerciseWithDropset>>

    @Query("""
        SELECT * FROM exercises_table
        LEFT JOIN dropsets_table ON exercises_table.id == dropsets_table.exercise_id
        JOIN exercises_to_muscles_cross_ref_table ON exercises_to_muscles_cross_ref_table.exercise_id == id
        WHERE name LIKE (:name) AND id NOT IN (:exercisesToExclude) AND muscle IN (:muscles)
        ORDER BY name
        """)
    fun getAllExercisesWithDropsetsByNameAndMusclesWithoutSessionExercises(
        name: String,
        exercisesToExclude: List<Int>,
        muscles: List<Muscle>
    ): LiveData<List<ExerciseWithDropset>>

    @Query("SELECT * FROM exercises_table LEFT JOIN dropsets_table ON id == dropsets_table.exercise_id JOIN exercises_to_muscles_cross_ref_table ON exercises_to_muscles_cross_ref_table.exercise_id == id WHERE muscle IN (:muscles) ORDER BY name")
    fun getAllExercisesWithDropsetsByMuscles(muscles: List<Muscle>): LiveData<List<ExerciseWithDropset>>

    @Query("SELECT * FROM exercises_table LEFT JOIN dropsets_table ON id == dropsets_table.exercise_id JOIN exercises_to_muscles_cross_ref_table ON exercises_to_muscles_cross_ref_table.exercise_id == id WHERE muscle IN (:muscles) AND name LIKE (:name) ORDER BY name")
    fun getAllExercisesWithDropsetsByMusclesAndName(name: String, muscles: List<Muscle>): LiveData<List<ExerciseWithDropset>>

//    @Query("SELECT * FROM exercises_table JOIN exercises_to_muscles_cross_ref_table ON exercise_id == id WHERE muscle IN (:muscles) AND name LIKE (:name) ORDER BY name")
//    fun getAllExercisesByNameAndMuscles(name: String, muscles: List<Muscle>): LiveData<List<ExerciseWithDropset>>
}