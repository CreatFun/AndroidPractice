package com.example.androidpractice.profile.domain

import com.example.androidpractice.domain.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(photoUri: String, name: String, url: String): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}