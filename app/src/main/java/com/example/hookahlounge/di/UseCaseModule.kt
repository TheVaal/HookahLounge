package com.example.hookahlounge.di

import com.example.hookahlounge.domain.usecase.SessionUseCase
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
import com.example.hookahlounge.domain.usecase.HardnessUseCase
import com.example.hookahlounge.domain.usecase.HookahMixUseCase
import com.example.hookahlounge.domain.usecase.InOrderUseCase
import com.example.hookahlounge.domain.usecase.LoungeMenuUseCase
import com.example.hookahlounge.domain.usecase.LoungeTobaccoUseCase
import com.example.hookahlounge.domain.usecase.LoungeUseCase
import com.example.hookahlounge.domain.usecase.ManufacturerUseCase
import com.example.hookahlounge.domain.usecase.MenuUseCase
import com.example.hookahlounge.domain.usecase.OrderUseCase
import com.example.hookahlounge.domain.usecase.TableUseCase
import com.example.hookahlounge.domain.usecase.TobaccoUseCase
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

    @Provides
    fun provideLoungeTobaccoUseCase(
        loungeTobaccoDbRepository: LoungeTobaccoDbRepository,
        loungeTobaccoRepository: LoungeTobaccoRepository,
    ): LoungeTobaccoUseCase {
        return LoungeTobaccoUseCase(
            loungeTobaccoDbRepository = loungeTobaccoDbRepository,
            loungeTobaccoRepository = loungeTobaccoRepository
        )
    }

    @Provides
    fun provideHardnessUseCase(
        hardnessTobaccoRepository: HardnessRepository,
        hardnessTobaccoDbRepository: HardnessDbRepository,
    ): HardnessUseCase {
        return HardnessUseCase(
            hardnessRepository = hardnessTobaccoRepository,
            hardnessDbRepository = hardnessTobaccoDbRepository,
        )
    }

    @Provides
    fun provideManufacturerUseCase(
        manufacturerRepository: ManufacturerRepository,
        manufacturerDbRepository: ManufacturerDbRepository,
    ): ManufacturerUseCase {
        return ManufacturerUseCase(
            manufacturerRepository = manufacturerRepository,
            manufacturerDbRepository = manufacturerDbRepository,
        )
    }

    @Provides
    fun provideTobaccoUseCase(
        tobaccoDbRepository: TobaccoDbRepository,
        tobaccoRepository: TobaccoRepository,
    ): TobaccoUseCase {
        return TobaccoUseCase(
            tobaccoDbRepository = tobaccoDbRepository,
            tobaccoRepository = tobaccoRepository
        )
    }

    @Provides
    fun provideOrderUseCase(
        orderDbRepository: OrderDbRepository,
        orderRepository: OrderRepository,
    ): OrderUseCase {
        return OrderUseCase(
            orderDbRepository = orderDbRepository,
            orderRepository = orderRepository
        )
    }

    @Provides
    fun provideInOrderUseCase(
        inOrderDbRepository: InOrderDbRepository,
        inOrderRepository: InOrderRepository,
    ): InOrderUseCase {
        return InOrderUseCase(
            inOrderDbRepository = inOrderDbRepository,
            inOrderRepository = inOrderRepository
        )
    }

    @Provides
    fun provideHookahMixUseCase(
        hookahMixDbRepository: HookahMixDbRepository,
        hookahMixRepository: HookahMixRepository,
    ): HookahMixUseCase {
        return HookahMixUseCase(
            hookahMixDbRepository = hookahMixDbRepository,
            hookahMixRepository = hookahMixRepository
        )
    }
}
