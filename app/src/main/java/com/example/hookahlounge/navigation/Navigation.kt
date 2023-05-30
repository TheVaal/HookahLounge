package com.example.hookahlounge.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@NavGraph
annotation class LoungeNavGraph(
    val start: Boolean = false
)

@NavGraph
annotation class OrderNavGraph(
    val start: Boolean = false
)

@NavGraph
annotation class SessionNavGraph(
    val start: Boolean = false
)