package com.example.hookahlounge.di

import com.example.hookahlounge.data.repository.api.AuthorizationRepositoryImpl
import com.example.hookahlounge.data.repository.api.HardnessRepositoryImpl
import com.example.hookahlounge.data.repository.api.HookahMixRepositoryImpl
import com.example.hookahlounge.data.repository.api.InOrderRepositoryImpl
import com.example.hookahlounge.data.repository.api.LoungeMenuRepositoryImpl
import com.example.hookahlounge.data.repository.api.LoungeRepositoryImpl
import com.example.hookahlounge.data.repository.api.LoungeTobaccoRepositoryImpl
import com.example.hookahlounge.data.repository.api.ManufacturerRepositoryImpl
import com.example.hookahlounge.data.repository.api.MenuRepositoryImpl
import com.example.hookahlounge.data.repository.api.OrderRepositoryImpl
import com.example.hookahlounge.data.repository.api.SessionRepositoryImpl
import com.example.hookahlounge.data.repository.api.TableRepositoryImpl
import com.example.hookahlounge.data.repository.api.TobaccoRepositoryImpl
import com.example.hookahlounge.data.repository.datastore.UserPrefsDataStoreRepositoryImpl
import com.example.hookahlounge.data.repository.local.HardnessDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.HookahMixDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.InOrderDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.LoungeDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.LoungeMenuDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.LoungeTobaccoDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.ManufacturerDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.MenuDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.OrderDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.SessionDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.TableDbRepositoryImpl
import com.example.hookahlounge.data.repository.local.TobaccoDbRepositoryImpl
import com.example.hookahlounge.domain.repository.api.AuthorizationRepository
import com.example.hookahlounge.domain.repository.api.HardnessRepository
import com.example.hookahlounge.domain.repository.api.HookahMixRepository
import com.example.hookahlounge.domain.repository.api.InOrderRepository
import com.example.hookahlounge.domain.repository.api.LoungeMenuRepository
import com.example.hookahlounge.domain.repository.api.LoungeRepository
import com.example.hookahlounge.domain.repository.api.LoungeTobaccoRepository
import com.example.hookahlounge.domain.repository.api.ManufacturerRepository
import com.example.hookahlounge.domain.repository.api.MenuRepository
import com.example.hookahlounge.domain.repository.api.OrderRepository
import com.example.hookahlounge.domain.repository.api.SessionRepository
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.repository.api.TobaccoRepository
import com.example.hookahlounge.domain.repository.datastore.UserPrefsDataStoreRepository
import com.example.hookahlounge.domain.repository.local.HardnessDbRepository
import com.example.hookahlounge.domain.repository.local.HookahMixDbRepository
import com.example.hookahlounge.domain.repository.local.InOrderDbRepository
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import com.example.hookahlounge.domain.repository.local.LoungeMenuDbRepository
import com.example.hookahlounge.domain.repository.local.LoungeTobaccoDbRepository
import com.example.hookahlounge.domain.repository.local.ManufacturerDbRepository
import com.example.hookahlounge.domain.repository.local.MenuDbRepository
import com.example.hookahlounge.domain.repository.local.OrderDbRepository
import com.example.hookahlounge.domain.repository.local.SessionDbRepository
import com.example.hookahlounge.domain.repository.local.TableDbRepository
import com.example.hookahlounge.domain.repository.local.TobaccoDbRepository
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
    abstract fun bindHookahRepository(repository: HookahMixRepositoryImpl): HookahMixRepository

    @Binds
    @Reusable
    abstract fun bindHookahDbRepository(repository: HookahMixDbRepositoryImpl): HookahMixDbRepository

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
    abstract fun bindLoungeMenuDbRepository(repository: LoungeMenuDbRepositoryImpl): LoungeMenuDbRepository

    @Binds
    @Reusable
    abstract fun bindLoungeMenuRepository(repository: LoungeMenuRepositoryImpl): LoungeMenuRepository

    @Binds
    @Reusable
    abstract fun bindMenuDbRepository(repository: MenuDbRepositoryImpl): MenuDbRepository

    @Binds
    @Reusable
    abstract fun bindMenuRepository(repository: MenuRepositoryImpl): MenuRepository

    @Binds
    @Reusable
    abstract fun bindSessionDbRepository(repository: SessionDbRepositoryImpl): SessionDbRepository

    @Binds
    @Reusable
    abstract fun bindLoungeTobaccoRepository(repository: LoungeTobaccoRepositoryImpl): LoungeTobaccoRepository

    @Binds
    @Reusable
    abstract fun bindLoungeTobaccoDbRepository(repository: LoungeTobaccoDbRepositoryImpl): LoungeTobaccoDbRepository

    @Binds
    @Reusable
    abstract fun bindTobaccoRepository(repository: TobaccoRepositoryImpl): TobaccoRepository

    @Binds
    @Reusable
    abstract fun bindTobaccoDbRepository(repository: TobaccoDbRepositoryImpl): TobaccoDbRepository

    @Binds
    @Reusable
    abstract fun bindHardnessRepository(repository: HardnessRepositoryImpl): HardnessRepository

    @Binds
    @Reusable
    abstract fun bindHardnessDbRepository(repository: HardnessDbRepositoryImpl): HardnessDbRepository

    @Binds
    @Reusable
    abstract fun bindManufacturerRepository(repository: ManufacturerRepositoryImpl): ManufacturerRepository

    @Binds
    @Reusable
    abstract fun bindManufacturerDbRepository(repository: ManufacturerDbRepositoryImpl): ManufacturerDbRepository

    @Binds
    @Reusable
    abstract fun bindOrderRepository(repository: OrderRepositoryImpl): OrderRepository

    @Binds
    @Reusable
    abstract fun bindOrderDbRepository(repository: OrderDbRepositoryImpl): OrderDbRepository

    @Binds
    @Reusable
    abstract fun bindInOrderRepository(repository: InOrderRepositoryImpl): InOrderRepository

    @Binds
    @Reusable
    abstract fun bindInOrderDbRepository(repository: InOrderDbRepositoryImpl): InOrderDbRepository

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryAppModule {

    @Binds
    @Reusable
    abstract fun bindTableRepository(repository: TableRepositoryImpl): TableRepository

    @Binds
    @Reusable
    abstract fun bindSessionRepository(repository: SessionRepositoryImpl): SessionRepository

    @Binds
    @Reusable
    abstract fun bindUserPreferences(repository: UserPrefsDataStoreRepositoryImpl): UserPrefsDataStoreRepository

    @Binds
    @Reusable
    abstract fun bindAuthorizationRepository(repository: AuthorizationRepositoryImpl): AuthorizationRepository

}