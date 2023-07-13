package com.example.hookahlounge.data.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<UserPreference> {
    override val defaultValue: UserPreference get() = UserPreference()
    private val stringFormat: StringFormat = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    override suspend fun readFrom(input: InputStream): UserPreference =
        try {
            stringFormat.decodeFromString(
                UserPreference.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("Cannot read stored data.", exception)
        }

    override suspend fun writeTo(t: UserPreference, output: OutputStream) {
        // writeTo is already called on the data store background thread
        @Suppress("BlockingMethodInNonBlockingContext")
        output.write(
            stringFormat.encodeToString(UserPreference.serializer(), t).encodeToByteArray()
        )
    }

}
