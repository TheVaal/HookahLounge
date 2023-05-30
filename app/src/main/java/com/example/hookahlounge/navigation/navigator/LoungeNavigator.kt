package com.example.hookahlounge.navigation.navigator

import com.example.hookahlounge.util.BaseNavigator

interface LoungeNavigator : BaseNavigator {

    fun toLounge(id:Long?)
}