package com.example.androidpractice.profile.data.serializer

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.androidpractice.domain.model.ProfileEntity
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.serialization.Serializer
import java.io.InputStream
import java.io.OutputStream

object ProfileSerializer : androidx.datastore.core.Serializer<ProfileEntity> {
    override val defaultValue: ProfileEntity
        get() = ProfileEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProfileEntity {
        try {
            return ProfileEntity.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: ProfileEntity, output: OutputStream) {
        t.writeTo(output)
    }
}

class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}