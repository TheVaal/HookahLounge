package com.example.hookahlounge.data.entity.core

import androidx.room.RoomDatabase
import com.example.hookahlounge.data.dao.HardnessDao
import com.example.hookahlounge.data.dao.HookahDao
import com.example.hookahlounge.data.dao.LoungeDao
import com.example.hookahlounge.data.dao.MenuDao
import com.example.hookahlounge.data.dao.OrderDao
import com.example.hookahlounge.data.dao.SessionDao
import com.example.hookahlounge.data.dao.UserDao
import com.example.hookahlounge.data.dao.WaiterCallDao

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