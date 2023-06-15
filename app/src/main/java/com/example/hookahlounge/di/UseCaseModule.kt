package com.example.hookahlounge.di

import com.example.hookahlounge.domain.repository.api.LoungeRepository
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import com.example.hookahlounge.domain.repository.local.TableDbRepository
import com.example.hookahlounge.domain.usecase.LoungeUseCase
import com.example.hookahlounge.domain.usecase.TableUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideLoungeUseCase(
        loungeDbRepository: LoungeDbRepository,
        loungeRepository: LoungeRepository,
    ): LoungeUseCase {
        return LoungeUseCase(
            loungeDbRepository = loungeDbRepository,
            loungeRepository = loungeRepository
        )
    }

    @Provides
    fun provideTableUseCase(
        tableDbRepository: TableDbRepository,
        tableRepository: TableRepository,
    ): TableUseCase {
        return TableUseCase(
            tableDbRepository = tableDbRepository,
            tableRepository = tableRepository
        )
    }
}
