package com.example.data.local.dao

import androidx.room.*
import com.example.data.model.SportsmanEntity

@Dao
interface ProfileDao {

    @Insert(entity = SportsmanEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: SportsmanEntity)

    @Update(entity = SportsmanEntity::class)
    suspend fun updateUser(user: SportsmanEntity)

    @Delete(entity = SportsmanEntity::class)
    suspend fun deleteUser(user: SportsmanEntity)

    @Query("SELECT * FROM sportsman_table")
    suspend fun readUserTable(): SportsmanEntity?

    @Query("SELECT COUNT(*) FROM sportsman_table")
    suspend fun getSizeSportsmanTable(): Int


}