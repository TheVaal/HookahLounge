package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.HookahEntity
import com.example.hookahlounge.data.entity.core.MixEntity
import com.example.hookahlounge.data.entity.projection.MixWithFields
import com.example.hookahlounge.domain.repository.local.HookahMixDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HookahMixDbRepositoryImpl@Inject constructor(orderDb: HookahLoungeDatabase) :
    HookahMixDbRepository {
    private val hookahDao = orderDb.getHookahDao()

    override suspend fun upsertHookah(hookah: HookahEntity) {
        hookahDao.upsertHookah(hookah)
    }

    override suspend fun upsertHookahs(list: List<HookahEntity>) {
        hookahDao.upsertAllHookah(list)
    }

    override suspend fun getMix(orderId: Long): Flow<List<MixWithFields>> {
        return hookahDao.getMixesByOrder(orderId)
    }

    override suspend fun getMixById(mixId: Long): Flow<MixWithFields> {
        return hookahDao.getMixById(mixId)
    }

    override suspend fun upsertMix(mix: MixEntity) {
        hookahDao.upsertMix(mix)
    }

    override suspend fun upsertMixes(list: List<MixWithFields>) {
        hookahDao.upsertAllMixes(list.map { it.mix })
        list.forEach {
            hookahDao.upsertAllHookah(it.hookah.map { item-> item.hookah})
        }
    }
}