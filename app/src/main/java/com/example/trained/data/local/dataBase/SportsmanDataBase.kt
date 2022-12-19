package com.example.trained.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trained.data.local.dao.ProfileDao
import com.example.trained.data.model.SportsmanModel

@Database(entities = [SportsmanModel::class], version = 1, exportSchema = false)
abstract class SportsmanDataBase: RoomDatabase() {
    abstract fun profileDao() : ProfileDao
}