package com.example.androidpractice.listWithDetails.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieDbEntity (
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "movieName") val movieName: String?,
    @ColumnInfo(name = "movieYear") val movieYear: String?,
    @ColumnInfo(name = "movieType") val movieType: String?,
    @ColumnInfo(name = "movieImgUrl") val movieImgUrl: String?
)