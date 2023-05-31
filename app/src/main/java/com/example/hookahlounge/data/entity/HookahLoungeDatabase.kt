package com.example.hookahlounge.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hookahlounge.data.dao.HardnessDao
import com.example.hookahlounge.data.dao.HookahDao
import com.example.hookahlounge.data.dao.LoungeDao
import com.example.hookahlounge.data.dao.MenuDao
import com.example.hookahlounge.data.dao.OrderDao
import com.example.hookahlounge.data.dao.SessionDao
import com.example.hookahlounge.data.dao.UserDao
import com.example.hookahlounge.data.dao.WaiterCallDao
import com.example.hookahlounge.data.entity.core.HardnessEntity
import com.example.hookahlounge.data.entity.core.HookahEntity
import com.example.hookahlounge.data.entity.core.InOrderEntity
import com.example.hookahlounge.data.entity.core.LoungeEntity
import com.example.hookahlounge.data.entity.core.LoungeMenuEntity
import com.example.hookahlounge.data.entity.core.LoungeTobaccoEntity
import com.example.hookahlounge.data.entity.core.ManufacturerEntity
import com.example.hookahlounge.data.entity.core.MenuEntity
import com.example.hookahlounge.data.entity.core.MixEntity
import com.example.hookahlounge.data.entity.core.OrderEntity
import com.example.hookahlounge.data.entity.core.SessionEntity
import com.example.hookahlounge.data.entity.core.TableEntity
import com.example.hookahlounge.data.entity.core.TobaccoEntity
import com.example.hookahlounge.data.entity.core.WaiterCallEntity

@Database(
    entities = [
        HardnessEntity::class,
        HookahEntity::class,
        InOrderEntity::class,
        LoungeEntity::class,
        LoungeMenuEntity::class,
        LoungeTobaccoEntity::class,
        ManufacturerEntity::class,
        MenuEntity::class,
        MixEntity::class,
        OrderEntity::class,
        SessionEntity::class,
        TableEntity::class,
        TobaccoEntity::class,
        WaiterCallEntity::class,
    ],
    version = 1
)
abstract class HookahLoungeDatabase : RoomDatabase() {
    abstract fun getHardnessDao() : HardnessDao
    abstract fun getHookahDao() : HookahDao
    abstract fun getLoungeDao() : LoungeDao
    abstract fun getMenuDao() : MenuDao
    abstract fun getOrderDao() : OrderDao
    abstract fun getSessionDao() : SessionDao
    abstract fun getUserDao() : UserDao
    abstract fun getWaiterCallDao() : WaiterCallDao
}