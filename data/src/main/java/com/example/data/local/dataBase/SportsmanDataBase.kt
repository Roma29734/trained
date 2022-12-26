package com.example.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ProfileDao
import com.example.data.model.SportsmanEntity

@Database(entities = [SportsmanEntity::class], version = 1, exportSchema = false)
abstract class SportsmanDataBase: RoomDatabase() {
    abstract fun profileDao() : ProfileDao
}