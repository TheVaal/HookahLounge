package com.example.hookahlounge.di

import androidx.compose.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.hookahlounge.data.dto.HookahLoungeApi
import com.example.hookahlounge.data.dto.mediators.LoungeRemoteMediator
import com.example.hookahlounge.data.dto.mediators.OrderRemoteMediator
import com.example.hookahlounge.data.dto.mediators.SessionRemoteMediator
import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.projection.OrderWithFields
import com.example.hookahlounge.domain.repository.api.TableRepository
import com.example.hookahlounge.domain.util.AppCoroutineDispatchers
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
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
    fun provideHookahApi(): HookahLoungeApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(HookahLoungeApi.BASE_URL)
            .build()
            .create(HookahLoungeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHookahDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        HookahLoungeDatabase::class.java,
        "hookah_lounge_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideLoungeDao(db: HookahLoungeDatabase) = db.getLoungeDao()

    @Provides
    @Singleton
    fun provideLoungeEntityPager(
        loungeDb: HookahLoungeDatabase,
        loungeApi: HookahLoungeApi,
    ): Pager<Int, LoungeEntity> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = LoungeRemoteMediator(
                loungeDb = loungeDb,
                loungeApi = loungeApi
            ),
            pagingSourceFactory = {
                loungeDb.getLoungeDao().pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideOrderEntityPager(
        orderDb: HookahLoungeDatabase,
        orderApi: HookahLoungeApi,
        tableRepository: TableRepository
    ): Pager<Int, OrderWithFields> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = OrderRemoteMediator(
                orderDb = orderDb,
                orderApi = orderApi,
                tableRepository = tableRepository
            ),
            pagingSourceFactory = {
                orderDb.getOrderDao().pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideSessionEntityPager(
        sessionDb: HookahLoungeDatabase,
        sessionApi: HookahLoungeApi,
    ): Pager<Int, SessionEntity> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = SessionRemoteMediator(
                sessionDb = sessionDb,
                sessionApi = sessionApi
            ),
            pagingSourceFactory = {
                sessionDb.getSessionDao().pagingSource()
            }
        )
    }

}