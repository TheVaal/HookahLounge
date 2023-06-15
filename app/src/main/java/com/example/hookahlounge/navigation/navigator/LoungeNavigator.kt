package com.example.hookahlounge.navigation.navigator

import com.example.hookahlounge.domain.util.BaseNavigator

interface LoungeNavigator : BaseNavigator {

    fun toLounge(id:Long?)
    fun toTable(id:Long)
    fun toNewTable(loungeId:Long?)
}