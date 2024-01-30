package uk.ac.aber.dcs.cs31620.tjamfitness.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos.DropsetDao
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos.ExerciseDao
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos.ExerciseWithDropsetDao
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos.ExerciseWithMuscleDao
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos.SessionDao
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.daos.SessionWithExerciseDao
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.DropsetEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseWithMuscleEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionWithExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.typeconverters.DayConverter
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.typeconverters.MuscleConverter
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle

/**
 * The Room Database
 */
@Database(
    entities =
    [
        DropsetEntity::class,
        ExerciseEntity::class,
        ExerciseWithMuscleEntity::class,
        SessionEntity::class,
        SessionWithExerciseEntity::class,
    ],
    version = 1,
)
@TypeConverters(
    DayConverter::class,
    MuscleConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dropsetDao(): DropsetDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exerciseWithDropsetDao(): ExerciseWithDropsetDao
    abstract fun exerciseWithMuscleDao(): ExerciseWithMuscleDao
    abstract fun sessionDao(): SessionDao
    abstract fun sessionWithExerciseDao(): SessionWithExerciseDao

    companion object {
        private var instance: AppDatabase? = null
        private var coroutineScope = CoroutineScope(Dispatchers.IO)

        @Synchronized
        fun getDatabase(context: Context): AppDatabase? {
            if (instance == null)
            {
                instance =
                    Room.databaseBuilder<AppDatabase>(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "fitness_app_database"
                    ).addCallback(roomDatabaseCallback(context)).build()
            }
            return instance
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            }
        }
    }
}