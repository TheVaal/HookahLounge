package com.example.hookahlounge.navigation.navigator

import com.example.hookahlounge.domain.util.BaseNavigator

interface OrderNavigator : BaseNavigator {
    fun toOrder(id:Long?)
}