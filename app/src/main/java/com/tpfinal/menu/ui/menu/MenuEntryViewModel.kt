package com.tpfinal.menu.ui.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tpfinal.menu.data.Menu
import com.tpfinal.menu.data.MenusRepository
import java.text.NumberFormat


class MenuEntryViewModel(private val itemsRepository: MenusRepository) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    fun updateUiState(itemDetails: MenuDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: MenuDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && description.isNotBlank()
        }
    }
}

data class ItemUiState(
    val itemDetails: MenuDetails = MenuDetails(),
    val isEntryValid: Boolean = false
)

data class MenuDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val description: String = "",
)

fun MenuDetails.toItem(): Menu = Menu(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    description = description
)

fun Menu.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

fun Menu.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Menu.toItemDetails(): MenuDetails = MenuDetails(
    id = id,
    name = name,
    price = price.toString(),
    description = description
)