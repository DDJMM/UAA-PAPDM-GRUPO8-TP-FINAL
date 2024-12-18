package com.tpfinal.menu.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import com.tpfinal.menu.MenuApplication
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tpfinal.menu.ui.home.InicioViewModel
import com.tpfinal.menu.ui.menu.MenuEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory{

        initializer {
            MenuEntryViewModel(menuApplication().container.menusRepository)
        }

        // Initializer for HomeViewModel
        initializer {
            InicioViewModel(menuApplication().container.menusRepository)
        }
    }
}

fun CreationExtras.menuApplication(): MenuApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MenuApplication)