package com.tpfinal.menu

import android.app.Application
import com.tpfinal.menu.data.AppContainer
import com.tpfinal.menu.data.AppDataContainer


class MenuApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
