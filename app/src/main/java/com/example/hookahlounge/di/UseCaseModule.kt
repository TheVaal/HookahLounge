package com.example.hookahlounge.di

import com.example.hookahSession.domain.usecase.SessionUseCase
import com.example.hookahlounge.domain.repository.api.LoungeMenuRepository
import com.example.hookahlounge.domain.repository.api.LoungeRepository
import com.example.hookahlounge.domain.repository.api.MenuRepository
import com.example.hookahlounge.domain.repository.api.SessionRepository
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import com.example.hookahlounge.domain.repository.local.LoungeMenuDbRepository
import com.example.hookahlounge.domain.repository.local.MenuDbRepository
import com.example.hookahlounge.domain.repository.local.SessionDbRepository
import com.example.hookahlounge.domain.repository.local.TableDbRepository
import com.example.hookahlounge.domain.usecase.LoungeMenuUseCase
import com.example.hookahlounge.domain.usecase.LoungeUseCase
import com.example.hookahlounge.domain.usecase.MenuUseCase
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
    fun provideLoungeMenuUseCase(
        loungeMenuDbRepository: LoungeMenuDbRepository,
        loungeMenuRepository: LoungeMenuRepository,
    ): LoungeMenuUseCase {
        return LoungeMenuUseCase(
            loungeMenuDbRepository = loungeMenuDbRepository,
            loungeMenuRepository = loungeMenuRepository
        )
    }

    @Provides
    fun provideMenuUseCase(
        menuDbRepository: MenuDbRepository,
        menuRepository: MenuRepository,
    ): MenuUseCase {
        return MenuUseCase(
            menuDbRepository = menuDbRepository,
            menuRepository = menuRepository
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
    @Provides
    fun provideSessionUseCase(
        sessionDbRepository: SessionDbRepository,
        sessionRepository: SessionRepository,
    ): SessionUseCase {
        return SessionUseCase(
            sessionDbRepository = sessionDbRepository,
            sessionRepository = sessionRepository
        )
    }
}
