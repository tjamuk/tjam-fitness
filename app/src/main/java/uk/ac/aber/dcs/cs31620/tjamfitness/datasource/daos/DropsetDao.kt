package uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.DropsetEntity

/**
 * The direct access object for all methods related to DropsetEntity
 */
@Dao
interface DropsetDao {

    /**
     * Inserts a single dropset into dropsets_table
     */
    @Insert
    fun insertSingleDropset(dropset: DropsetEntity)

    /**
     * Inserts a multiple dropsets into dropsets_table
     */
    @Insert
    fun insertMultipleDropsets(dropsets: List<DropsetEntity>)

    /**
     * updates a single dropset from dropsets_table
     */
    @Update
    fun updateSingleDropset(dropset: DropsetEntity)

    /**
     * deletes a single dropset from dropsets_table
     */
    @Delete
    fun deleteSingleDropset(dropset: DropsetEntity)
}