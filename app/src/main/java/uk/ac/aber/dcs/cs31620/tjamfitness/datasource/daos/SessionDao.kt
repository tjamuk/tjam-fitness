package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Update
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.SessionInfo

/**
 * The direct access object for all methods somewhat related to sessionEntity and the sessions_table table.
 */
@Dao
interface SessionDao {
    @Insert
    fun insertSingleSession(session: SessionEntity)

    @Query("DELETE FROM sessions_table WHERE day == :day")
    fun deleteSingleSession(day: Day)

    @Delete
    fun deleteMultipleSessions(sessions: List<SessionEntity>)

    @Query("DELETE FROM sessions_table WHERE day IN (:days)")
    fun deleteMultipleSessionsThroughDay(days: List<Int>)

    @Query("SELECT EXISTS(SELECT * FROM sessions_table WHERE day == :day)")
    fun doesDayHaveSession(day: Day): Boolean

    @Update
    fun updateSingleSession(session: SessionEntity)

    @Update
    fun updateMultipleSessions(sessions: List<SessionEntity>)

    @Query("SELECT * from sessions_table")
    fun getAllSessions() : LiveData<Map<
            @MapColumn("day")
            Day,

            SessionInfo
    >>

    @Query(
        """
            UPDATE sessions_table
            SET description = :newDescription
            WHERE day = :day
            """
    )
    fun updateSessionDesc(day: Day, newDescription: String)

}