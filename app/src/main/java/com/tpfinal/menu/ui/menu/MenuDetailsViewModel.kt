package com.tpfinal.menu.ui.menu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpfinal.menu.data.MenusRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class MenuDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val menusRepository: MenusRepository,
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[MenuDetailsDestination.itemIdArg])


    val uiState: StateFlow<MenuDetailsUiState> =
        menusRepository.getItemStream(itemId)
            .filterNotNull()
            .map {
                MenuDetailsUiState(menuDetails = it.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = MenuDetailsUiState()
            )
    suspend fun deleteMenu() {
        menusRepository.deleteItem(uiState.value.menuDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class MenuDetailsUiState(

    val menuDetails: MenuDetails = MenuDetails()
)

