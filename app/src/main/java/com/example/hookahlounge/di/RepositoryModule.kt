package com.example.hookahlounge.di

import com.example.hookahlounge.data.repository.api.HardnessRepositoryImpl
import com.example.hookahlounge.data.repository.api.HookahRepositoryImpl
import com.example.hookahlounge.data.repository.api.LoungeRepositoryImpl
import com.example.hookahlounge.data.repository.api.SessionRepositoryImpl
import com.example.hookahlounge.data.repository.api.TableRepositoryImpl
import com.example.hookahlounge.data.repository.api.TobaccoRepositoryImpl
import com.example.hookahlounge.data.repository.local.LoungeDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.SessionDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.TableDbRepositoryImpl
import com.example.hookahlounge.domain.repository.api.HardnessRepository
import com.example.hookahlounge.domain.repository.api.HookahRepository
import com.example.hookahlounge.domain.repository.api.LoungeRepository
import com.example.hookahlounge.domain.repository.api.SessionRepository
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.repository.api.TobaccoRepository
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import com.example.hookahlounge.domain.repository.local.SessionDbRepository
import com.example.hookahlounge.domain.repository.local.TableDbRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun bindHardnessRepository(repository: HardnessRepositoryImpl): HardnessRepository

    @Binds
    @Reusable
    abstract fun bindHookahRepository(repository: HookahRepositoryImpl): HookahRepository


    @Binds
    @Reusable
    abstract fun bindTobaccoRepository(repository: TobaccoRepositoryImpl): TobaccoRepository

    @Binds
    @Reusable
    abstract fun bindTableDbRepository(repository: TableDbRepositoryImpl): TableDbRepository

    @Binds
    @Reusable
    abstract fun bindLoungeDbRepository(repository: LoungeDbRepositoryImpl): LoungeDbRepository

    @Binds
    @Reusable
    abstract fun bindLoungeRepository(repository: LoungeRepositoryImpl): LoungeRepository

    @Binds
    @Reusable
    abstract fun bindSessionRepository(repository: SessionRepositoryImpl): SessionRepository

    @Binds
    @Reusable
    abstract fun bindSessionDbRepository(repository: SessionDbRepositoryImpl): SessionDbRepository


}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryAppModule {

    @Binds
    @Reusable
    abstract fun bindTableRepository(repository: TableRepositoryImpl): TableRepository

}