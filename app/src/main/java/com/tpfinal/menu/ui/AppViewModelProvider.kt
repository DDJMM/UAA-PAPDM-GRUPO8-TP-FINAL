package com.tpfinal.menu.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import com.tpfinal.menu.MenuApplication
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tpfinal.menu.ui.home.InicioViewModel
import com.tpfinal.menu.ui.menu.MenuDetailsViewModel
import com.tpfinal.menu.ui.menu.MenuEditViewModel
import com.tpfinal.menu.ui.menu.MenuEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory{

        initializer {
            MenuEntryViewModel(
                menuApplication().container.menusRepository
            )
        }

        initializer {
            InicioViewModel(
                menuApplication().container.menusRepository
            )
        }
        initializer {
            MenuDetailsViewModel(
                this.createSavedStateHandle(),
                menuApplication().container.menusRepository
            )
        }
        initializer {
            MenuEditViewModel(
                this.createSavedStateHandle(),
                menuApplication().container.menusRepository
            )
        }
    }
}

fun CreationExtras.menuApplication(): MenuApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MenuApplication)