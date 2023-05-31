package com.example.hookahlounge.di

import androidx.compose.Context
import androidx.room.Room
import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.util.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Provides
    @Singleton
    fun provideHookahDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        HookahLoungeDatabase::class.java,
        "hookah_lounge_db"
    ).fallbackToDestructiveMigration().build()

}