package com.example.hookahlounge.navigation.navigator

import com.example.hookahlounge.domain.util.BaseNavigator

interface LoungeNavigator : BaseNavigator {

    fun toLounge(id: Long?)
    fun toTable(id: Long)
    fun toNewTable(loungeId: Long?)
    fun toMenuList(loungeId: Long?)

    fun toLoungeMenu(
        loungeMenuId: Long = 0L,
        menuId: Long = 0L,
        loungeId: Long = 0L,
    )

    fun toTobaccoList(loungeId: Long?)

    fun toLoungeTobacco(
        loungeTobaccoId: Long = 0L,
        tobaccoId: Long = 0L,
        loungeId: Long = 0L,
    )
}