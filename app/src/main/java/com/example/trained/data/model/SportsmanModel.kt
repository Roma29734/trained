package com.example.trained.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sportsman_table")
data class SportsmanModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "age")val age: Int,
    @ColumnInfo(name = "weight")val weight: Int,
    @ColumnInfo(name = "growth")val growth: Int,
)