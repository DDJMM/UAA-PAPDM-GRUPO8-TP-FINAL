package com.tpfinal.menu.ui.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.tpfinal.menu.data.MenusRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

class MenuEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val menusRepository: MenusRepository
) : ViewModel() {

    var menuUiState by mutableStateOf(MenuUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[MenuEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            menuUiState = menusRepository.getItemStream(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(true)
        }
    }

    suspend fun updateMenu() {
        if (validateInput(menuUiState.menuDetails)) {
            menusRepository.updateItem(menuUiState.menuDetails.toItem())
        }
    }

    fun updateUiState(menuDetails: MenuDetails) {
        menuUiState =
            MenuUiState(menuDetails = menuDetails, isEntryValid = validateInput(menuDetails))
    }

    private fun validateInput(uiState: MenuDetails = menuUiState.menuDetails): Boolean {
        return with(uiState) {
            val priceInput = price.trim()
            name.isNotBlank() &&
                    priceInput.isNotEmpty() &&
                    priceInput.toIntOrNull() != null &&
                    priceInput.toInt() > 0 &&
                    description.isNotBlank()
        }
    }
//    private fun validateInput(uiState: MenuDetails = menuUiState.menuDetails): Boolean {
//        return with(uiState) {
//            name.isNotBlank() && price.isNotBlank() && description.isNotBlank()
//        }
//    }
}