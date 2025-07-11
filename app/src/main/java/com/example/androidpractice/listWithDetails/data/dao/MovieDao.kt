package com.example.androidpractice.listWithDetails.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpractice.listWithDetails.data.entity.MovieDbEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieDbEntity")
    suspend fun getAll(): List<MovieDbEntity>

    @Insert
    suspend fun insert(driverDbEntity: MovieDbEntity)
}