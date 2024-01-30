package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.Query
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionWithExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.model.ExerciseWithDropset

/**
 * Each session can have 0 or more exercises.
 *
 * This dao accesses table 'sessions_to_exercises_cross_ref_table' to get the exercises of a session, often times joining exercises_table and dropsets_table.
 */
@Dao
interface SessionWithExerciseDao {

    @Insert
    fun insertSingleSessionWithExercise(sessionToExercise: SessionWithExerciseEntity)

    @Query(
        """
            SELECT * 
            FROM sessions_to_exercises_cross_ref_table 
            JOIN exercises_table ON sessions_to_exercises_cross_ref_table.exercise_id == exercises_table.id 
            LEFT JOIN dropsets_table ON dropsets_table.exercise_id == exercises_table.id 
            WHERE session_day == :day 
            ORDER BY order_index
        """
    )
    fun getSessionExercisesWithDropsets(day: Day): LiveData<List<ExerciseWithDropset>>

    @Query(
        """
            SELECT *
            FROM sessions_to_exercises_cross_ref_table
        """
    )
    fun getSessionsToExercises(): LiveData<Map<

            @MapColumn(columnName = "session_day")
            Day,

            List<
                    @MapColumn(columnName = "exercise_id")
                    Int
            >
    >>

    @Query(
        """
            SELECT *
            FROM sessions_to_exercises_cross_ref_table
        """
    )
    fun getSessionsToExercisesMap(): Map<

            @MapColumn(columnName = "session_day")
            Day,

            List<
                    @MapColumn(columnName = "exercise_id")
                    Int
            >
    >?

    @Query(
        """
            SELECT exercise_id
            FROM sessions_to_exercises_cross_ref_table
            WHERE session_day == :day
        """
    )
    fun getSessionToExercisesList(day: Day): List<
            @MapColumn(columnName = "exercise_id")
            Int
    >?

    @Query("SELECT * FROM sessions_to_exercises_cross_ref_table JOIN exercises_table ON sessions_to_exercises_cross_ref_table.exercise_id == exercises_table.id LEFT JOIN dropsets_table ON dropsets_table.exercise_id == sessions_to_exercises_cross_ref_table.exercise_id WHERE session_day == :day ORDER BY order_index")
    fun getSessionExercisesWithDropsetsList(day: Day): List<ExerciseWithDropset>

    @Query("SELECT MAX(order_index) FROM sessions_to_exercises_cross_ref_table WHERE session_day = :day")
    fun getLastOrderIndexInSession(day: Day): LiveData<Int>

    @Query("SELECT MAX(order_index) FROM sessions_to_exercises_cross_ref_table WHERE session_day = :day")
    fun getLastOrderIndexInSessionInt(day: Day): Int?

    @Query("SELECT session_day,MAX(order_index) AS max_order_index FROM sessions_to_exercises_cross_ref_table GROUP BY session_day")
    fun getLastOrderIndexesInSessions(): LiveData<Map<

            @MapColumn("session_day")
            Int,

            @MapColumn("max_order_index")
            Int
    >>

    @Query("DELETE FROM sessions_to_exercises_cross_ref_table WHERE session_day == :day AND exercise_id IN (:exerciseIdList)")
    fun deleteSessionToExerciseLinks(day: Day, exerciseIdList: List<Int>)

    @Query(
        """
        SELECT muscle
        FROM sessions_to_exercises_cross_ref_table 
        JOIN exercises_table ON sessions_to_exercises_cross_ref_table.exercise_id == exercises_table.id
        JOIN exercises_to_muscles_cross_ref_table ON exercises_to_muscles_cross_ref_table.exercise_id == exercises_table.id
        WHERE session_day = :day
        """
    )
    suspend fun getSessionMuscles(day: Day): List<Muscle>



}