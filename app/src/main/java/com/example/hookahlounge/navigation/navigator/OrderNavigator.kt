package com.example.hookahlounge.navigation.navigator

import com.example.hookahlounge.util.BaseNavigator

interface OrderNavigator : BaseNavigator {
    fun toOrder(id:Long?)
}