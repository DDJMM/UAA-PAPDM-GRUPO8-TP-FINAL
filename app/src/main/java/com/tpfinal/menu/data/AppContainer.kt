package com.tpfinal.menu.data

import android.content.Context

interface AppContainer {
    val menusRepository: MenusRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val menusRepository: MenusRepository by lazy {
        OfflineMenuRepository(MenuDatabase.getDatabase(context).menuDao())
    }
}

