package com.example.trained.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trained.data.model.DayWorkoutModel
import com.example.trained.data.model.SportsmanModel
import com.example.trained.data.model.WorkoutModel

@Dao
interface ProfileDao {

    @Insert(entity = SportsmanModel::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: SportsmanModel)

    @Update(entity = SportsmanModel::class)
    suspend fun updateUser(user: SportsmanModel)

    @Delete(entity = SportsmanModel::class)
    suspend fun deleteUser(user: SportsmanModel)

    @Query("SELECT * FROM sportsman_table")
    suspend fun readUserTable(): SportsmanModel

    @Query("SELECT COUNT(*) FROM sportsman_table")
    suspend fun getSizeSportsmanTable(): Int


}