package com.example.androidpractice.profile.repository

import androidx.datastore.core.DataStore
import com.example.androidpractice.domain.model.ProfileEntity
import com.example.androidpractice.profile.domain.IProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class ProfileRepository: IProfileRepository {
    private val dataStore : DataStore<ProfileEntity> by inject(DataStore::class.java, named("profile"))

    override suspend fun observeProfile(): Flow<ProfileEntity> = dataStore.data

    override suspend fun getProfile(): ProfileEntity? = dataStore.data.firstOrNull()

    override suspend fun setProfile(photoUri: String, name: String, url: String, status: String) =
        dataStore.updateData {
            it.toBuilder().apply {
                this.photoUri = photoUri
                this.name = name
                this.url = url
                this.status = status
            }.build()
        }
}