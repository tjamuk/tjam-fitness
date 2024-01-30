package uk.ac.aber.dcs.cs31620.tjamfitness.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.AppRepository
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.DropsetEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.ExerciseWithMuscleEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.datasource.entities.SessionWithExerciseEntity
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Day
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.Muscle
import uk.ac.aber.dcs.cs31620.tjamfitness.model.enumerations.days

private val units = arrayOf("s", "m", "h")

class AppViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository: AppRepository = AppRepository(application)

    var sessionsMap: LiveData<Map<Day, SessionInfo>> = repository.getAllSessions()

    var exercises: LiveData<List<ExerciseWithDropset>> = repository.getSessionExercisesWithDropsets(
        Day.MONDAY)

    var whichDay: Day = Day.MONDAY

    var whichExercise: ExerciseWithDropset? = null

    private lateinit var lastOrderIndexInSession: LiveData<Int>

    lateinit var exerciseMuscles: LiveData<List<Muscle>>

    var exercisesWithDropsets: LiveData<List<ExerciseWithDropset>> = repository.getAllExercisesWithDropsetsByName("")

    private var mostRecentlyAddedExerciseId: Int? = null

    fun deleteSessions(days: List<Int>)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.deleteMultipleSessionsThroughDay(days)
        }
    }

    fun insertExercise(exercise: ExerciseEntity, firstSet: Int? = null, secondSet: Int? = null, thirdSet: Int? = null, muscles: List<Muscle>? = null)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insertSingleExercise(exercise)
            mostRecentlyAddedExerciseId = repository.getSingleExerciseIdInt(
                exercise.name,
                exercise.sets,
                exercise.reps,
                exercise.weight,
                exercise.hasDropset
            )

            mostRecentlyAddedExerciseId?.let {
                if (exercise.hasDropset)
                {
                    if (firstSet != null && secondSet != null && thirdSet != null)
                    {
                        repository.insertSingleDropset(DropsetEntity(it, firstSet, secondSet, thirdSet))
                    }

                }

                muscles?.let { muscles ->
                    for (muscle in muscles)
                    {
                        repository.insertExerciseToMuscle(ExerciseWithMuscleEntity(it, muscle))
                    }
                }
            }
        }
    }

    fun addExerciseToSession(exerciseId: Int)
    {
        viewModelScope.launch(Dispatchers.IO)
        {

            if (!repository.doesDayHaveSession(whichDay))
            {
                repository.insertSingleSession(SessionEntity(whichDay, "Session", ""))
            }

            val sessionExercisesIdList = repository.getSessionToExercisesMap(whichDay)

            sessionExercisesIdList?.let {

                updateSessionDescription(
                    whichDay,
                    it.toMutableSet().plus(exerciseId)
                )
            }

            repository.insertSingleSessionWithExercise(
                SessionWithExerciseEntity(
                    exerciseId = exerciseId,
                    sessionDay = whichDay,
                    orderIndex =
                        if (lastOrderIndexInSession.value == null)
                        {
                            0
                        }
                        else
                        {
                            lastOrderIndexInSession.value!! + 1
                        }
                )
            )
        }
    }

    fun updateWhichDay(day: Day)
    {
        whichDay = day
        viewModelScope.launch(Dispatchers.IO)
        {
            exercises = repository.getSessionExercisesWithDropsets(day)
            lastOrderIndexInSession = repository.getLastOrderIndexInSession(day)
        }
    }

    fun updateExercises(
        name: String = "",
        muscles: List<Muscle> = listOf(),
        areSessionExercises: Boolean = true
    )
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            exercisesWithDropsets =
                if (!areSessionExercises || exercises.value != null)
                {
                        if (!areSessionExercises || exercises.value!!.isEmpty())
                        {
                            if (muscles.isEmpty())
                            {
                                repository.getAllExercisesWithDropsetsByName("%$name%")
                            }
                            else
                            {
                                repository.getAllExercisesWithDropsetsByMusclesAndName("%$name%", muscles)
                            }
                        }
                        else
                        {
                            val exerciseIdToExcludeList = mutableListOf<Int>()

                            exercises.value!!.forEach { exerciseAndDropset ->
                                exerciseIdToExcludeList.add(exerciseAndDropset.exercise.id)
                            }

                            if (muscles.isEmpty())
                            {
                                repository.getAllExercisesWithDropsetsByNameWithoutSessionExercises("%$name%", exerciseIdToExcludeList)
                            }
                            else
                            {
                                repository.getAllExercisesWithDropsetsByNameAndMusclesWithoutSessionExercises("%$name%", exerciseIdToExcludeList, muscles)
                            }
                        }
                }
                else
                {
                    repository.getAllExercisesWithDropsetsByName("%$name%")
                }
        }
    }

    fun deleteSessionToExerciseLinks(exerciseIdList: List<Int>)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val sessionExercisesIdList = repository.getSessionToExercisesMap(whichDay)

            sessionExercisesIdList?.let {
                updateSessionDescription(
                    whichDay,
                    it.toSet() subtract exerciseIdList.toSet()
                )
            }

            repository.deleteSessionToExerciseLinks(whichDay, exerciseIdList)
        }
    }

    fun deleteExercises(exerciseIdList: List<Int>)
    {

        viewModelScope.launch(Dispatchers.IO)
        {

            val sessionsToExercises = repository.getSessionsToExercisesMap()

            sessionsToExercises?.let { sessionsToExercisesMapping ->
                for (day in days)
                {
                    sessionsToExercisesMapping[day]?.let{ exercisesInSession ->

                        val newExercisesInSession = exercisesInSession.toSet() subtract exerciseIdList.toSet()

                        if (newExercisesInSession.size != exercisesInSession.size)
                        {
                            updateSessionDescription(day, newExercisesInSession)
                        }
                    }
                }
            }
            repository.deleteMultipleExercises(exerciseIdList)
        }
    }

    private suspend fun updateSessionDescription(day: Day, exercisesInSession: Set<Int>)
    {

        if (exercisesInSession.isEmpty())
        {
            repository.deleteSingleSession(day)
        }
        else
        {
            val newMuscles = repository.getMusclesFromExercises(exercisesInSession.toList())
            val newExercises = repository.getMultipleExercisesFromId(exercisesInSession.toList())

            val duration: Array<Int> = arrayOf(0,0,0)

            for (exercise in newExercises)
            {
                duration[0] += exercise.sets * exercise.reps * 3 + 60 * (exercise.sets-1)

                if (exercise.hasDropset)
                {
                    duration[0] += 360
                }
            }

            for (index in 1..2)
            {
                duration[index] = duration[index-1].floorDiv(60)
                duration[index-1] -= duration[index]*60
            }

            val stringBuilder: StringBuilder = StringBuilder()

            if (newMuscles.isNotEmpty())
            {
                stringBuilder.append(muscleGroupsToFocus(newMuscles.toSet()))
            }
            else
            {
                stringBuilder.append("Session")
            }
            stringBuilder.append(" • ")

            for (index in 2 downTo 0)
            {
                if (duration[index] != 0)
                {
                    stringBuilder.append(duration[index])
                    stringBuilder.append(units[index])
                }
            }
            repository.updateSessionDesc(day, stringBuilder.toString())
        }
    }

    fun updateWhichExercise(exerciseWithDropset: ExerciseWithDropset)
    {
        whichExercise = exerciseWithDropset
        exerciseMuscles = repository.getExerciseMusclesTest(exerciseWithDropset.exercise.id)
    }

    fun updateSingleExercise(exerciseEntity: ExerciseEntity, firstSet: Int? = null, secondSet: Int? = null, thirdSet: Int? = null, newMuscles: Set<Muscle> = emptySet(), oldMuscles: Set<Muscle> = emptySet())
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val sessionsToExercises = repository.getSessionsToExercisesMap()

            whichExercise?.let{
                repository.updateSingleExercise(exerciseEntity)

                if (exerciseEntity.hasDropset)
                {
                    if (it.exercise.hasDropset)
                    {
                        if (firstSet == null || secondSet == null || thirdSet == null)
                        {
                            repository.deleteSingleDropset(DropsetEntity(it.exercise.id, it.dropset.firstSet, it.dropset.secondSet, it.dropset.thirdSet))
                        }
                        else if (it.dropset.firstSet != firstSet || it.dropset.secondSet != secondSet || it.dropset.thirdSet != thirdSet)
                        {
                            repository.updateSingleDropset(DropsetEntity(exerciseEntity.id, firstSet, secondSet, thirdSet))
                        }
                    }
                    else
                    {
                        if (firstSet != null && secondSet != null && thirdSet != null)
                        {
                            repository.insertSingleDropset(DropsetEntity(it.exercise.id, firstSet, secondSet, thirdSet))
                        }
                    }
                }
                else
                {
                    if (it.exercise.hasDropset)
                    {
                        repository.deleteSingleDropset(DropsetEntity(it.exercise.id, it.dropset.firstSet, it.dropset.secondSet, it.dropset.thirdSet))
                    }
                }

                if (oldMuscles != newMuscles)
                {
                    var muscles = oldMuscles subtract newMuscles
                    if (muscles.isNotEmpty())
                    {
                        repository.deleteSomeExerciseMuscles(it.exercise.id, muscles.toList())
                    }

                    muscles = newMuscles subtract oldMuscles
                    if (muscles.isNotEmpty())
                    {
                        repository.insertMusclesForExercise(it.exercise.id, muscles.toList())
                    }
                }
            }

            sessionsToExercises?.let { sessionsToExercisesMapping ->
                for (day in days)
                {
                    sessionsToExercisesMapping[day]?.let{ exercisesInSession ->

                        if (exercisesInSession.contains(exerciseEntity.id))
                        {
                            updateSessionDescription(day, exercisesInSession.toSet())
                        }
                    }
                }
            }
        }
    }
}