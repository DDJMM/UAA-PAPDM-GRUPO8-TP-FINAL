package com.tpfinal.menu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpfinal.menu.data.Menu
import com.tpfinal.menu.data.MenusRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class InicioViewModel(menusRepository: MenusRepository) : ViewModel(){

    val inicioUiState: StateFlow<InicioUIState> =
        menusRepository.getAllItemsStream().map { InicioUIState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = InicioUIState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class InicioUIState(val menuList: List<Menu> = listOf())