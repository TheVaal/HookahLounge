package com.example.hookahlounge.data.repository.local

import com.example.hookahlounge.data.entity.HookahLoungeDatabase
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.projection.LoungeWithTables
import com.example.hookahlounge.domain.repository.local.LoungeDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoungeDbRepositoryImpl @Inject constructor(loungesDb: HookahLoungeDatabase) :
    LoungeDbRepository {

    private val loungeDao = loungesDb.getLoungeDao()
    private val tableDao = loungesDb.getTableDao()
    private val menuDao = loungesDb.getMenuDao()
    private val tobaccoDao = loungesDb.getTobaccoDao()
    private val manufacturerDao = loungesDb.getManufacturerDao()
    private val hardnessDao = loungesDb.getHardnessDao()


    override fun getLounge(id: Long): Flow<LoungeWithTables> {
        return loungeDao.getLoungeById(id)
    }

    override fun getLounges(): Flow<List<LoungeEntity>> {
        return loungeDao.getLounges()
    }

    override suspend fun upsertAll(lounges: List<LoungeEntity>) {
        loungeDao.upsertAll(lounges)
    }

    override suspend fun upsertLounge(lounge: LoungeWithTables) {
        loungeDao.upsert(lounge.lounge)
        tableDao.upsertAll(lounge.tables)

        hardnessDao.upsertAllHardness(lounge.tobacco.map { it.tobacco.hardness })
        manufacturerDao.upsertAllManufacturers(lounge.tobacco.map { it.tobacco.manufacturer })

        tobaccoDao.upsertAllTobacco(lounge.tobacco.map { it.tobacco.tobacco})
        tobaccoDao.upsertAllLoungeTobaccos(lounge.tobacco.map { it.loungeTobacco})

        menuDao.upsertAllMenus(lounge.menu.map { it.menu })
        menuDao.upsertAllLoungeMenus(lounge.menu.map { it.loungeMenu })
    }

    override suspend fun upsertLounge(lounge: LoungeEntity) {
        loungeDao.upsert(lounge)
    }

}