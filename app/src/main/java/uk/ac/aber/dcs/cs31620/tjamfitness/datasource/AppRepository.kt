package uk.ac.aber.dcs.cs31620.tjamfitness.datasource

import android.app.Application
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.DropsetEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseWithMuscleEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionWithExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle

/**
 * The only repository for this project so it uses all daos.
 *
 */
class AppRepository(application: Application) {
    private val dropsetDao = AppDatabase.getDatabase(application)!!.dropsetDao()
    private val exerciseDao = AppDatabase.getDatabase(application)!!.exerciseDao()
    private val exerciseWithDropsetDao = AppDatabase.getDatabase(application)!!.exerciseWithDropsetDao()
    private val exerciseWithMuscleDao = AppDatabase.getDatabase(application)!!.exerciseWithMuscleDao()
    private val sessionDao = AppDatabase.getDatabase(application)!!.sessionDao()
    private val sessionWithExerciseDao = AppDatabase.getDatabase(application)!!.sessionWithExerciseDao()

    //DropsetDao

    fun insertSingleDropset(dropset: DropsetEntity)
    {
        dropsetDao.insertSingleDropset(dropset)
    }

    fun updateSingleDropset(dropset: DropsetEntity)
    {
        dropsetDao.updateSingleDropset(dropset)
    }

    fun deleteSingleDropset(dropset: DropsetEntity)
    {
        dropsetDao.deleteSingleDropset(dropset)
    }

    //ExerciseDao

    fun insertSingleExercise(exercise: ExerciseEntity)
    {
        exerciseDao.insertSingleExercise(exercise)
    }

    fun updateSingleExercise(exercise: ExerciseEntity)
    {
        exerciseDao.updateSingleExercise(exercise)
    }

    fun deleteMultipleExercises(exerciseIds: List<Int>)
    {
        exerciseDao.deleteMultipleExercises(exerciseIds)
    }

    fun getSingleExerciseIdInt(name: String, sets: Int, reps: Int, weight: Int, hasDropset: Boolean) = exerciseDao.getSingleExerciseIdInt(name,sets,reps,weight,hasDropset)

    suspend fun getMultipleExercisesFromId(exerciseIdList: List<Int>) = exerciseDao.getMultipleExercisesFromId(exerciseIdList)

    //ExerciseWithDropsetDao

    fun getAllExercisesWithDropsetsByName(name: String) = exerciseWithDropsetDao.getAllExercisesWithDropsetsByName(name)

    fun getAllExercisesWithDropsetsByMusclesAndName(name: String, muscles: List<Muscle>) = exerciseWithDropsetDao.getAllExercisesWithDropsetsByMusclesAndName(name, muscles)

    fun getAllExercisesWithDropsetsByNameAndMusclesWithoutSessionExercises(
        name: String,
        exercisesToExclude: List<Int>,
        muscles: List<Muscle>
    ) = exerciseWithDropsetDao.getAllExercisesWithDropsetsByNameAndMusclesWithoutSessionExercises(
        name,
        exercisesToExclude,
        muscles
    )

    fun getAllExercisesWithDropsetsByNameWithoutSessionExercises(name: String, exercisesToExclude: List<Int>) = exerciseWithDropsetDao.getAllExercisesWithDropsetsByNameWithoutSessionExercises(name, exercisesToExclude)

    //ExerciseWithMuscleDao

    fun insertExerciseToMuscle(exerciseToMuscle: ExerciseWithMuscleEntity)
    {
        exerciseWithMuscleDao.insertExerciseToMuscleGroup(exerciseToMuscle)
    }

    fun getExerciseMusclesTest(exerciseId: Int) = exerciseWithMuscleDao.getExerciseMusclesTest(exerciseId)

    fun deleteSomeExerciseMuscles(exerciseId: Int, muscles: List<Muscle>) = exerciseWithMuscleDao.deleteSomeExerciseMuscles(exerciseId, muscles)

    fun insertMusclesForExercise(exerciseId: Int, muscles: List<Muscle>) = exerciseWithMuscleDao.insertMusclesForExercise(exerciseId, muscles)

    fun getMusclesFromExercises(exerciseIdList: List<Int>) = exerciseWithMuscleDao.getMusclesFromExercises(exerciseIdList)

    //SessionDao

    fun insertSingleSession(session: SessionEntity)
    {
        sessionDao.insertSingleSession(session)
    }

    fun deleteSingleSession(day: Day)
    {
        sessionDao.deleteSingleSession(day)
    }

    fun deleteMultipleSessionsThroughDay(days: List<Int>)
    {
        sessionDao.deleteMultipleSessionsThroughDay(days)
    }

    fun getAllSessions() = sessionDao.getAllSessions()

    fun doesDayHaveSession(day: Day) = sessionDao.doesDayHaveSession(day)

    fun updateSessionDesc(day: Day, newDescription: String) = sessionDao.updateSessionDesc(day, newDescription)

    //SessionWithExerciseDao

    fun insertSingleSessionWithExercise(sessionToExercise: SessionWithExerciseEntity)
    {
        sessionWithExerciseDao.insertSingleSessionWithExercise(sessionToExercise)
    }

    fun getSessionExercisesWithDropsets(day: Day) = sessionWithExerciseDao.getSessionExercisesWithDropsets(day)

    fun getLastOrderIndexInSession(day: Day) = sessionWithExerciseDao.getLastOrderIndexInSession(day)

    fun deleteSessionToExerciseLinks(day: Day, exerciseIdList: List<Int>) = sessionWithExerciseDao.deleteSessionToExerciseLinks(day, exerciseIdList)

    fun getSessionsToExercisesMap() = sessionWithExerciseDao.getSessionsToExercisesMap()

    fun getSessionToExercisesMap(day: Day) = sessionWithExerciseDao.getSessionToExercisesList(day)
}