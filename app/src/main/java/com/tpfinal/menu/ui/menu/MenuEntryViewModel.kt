package com.tpfinal.menu.ui.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tpfinal.menu.data.Menu
import com.tpfinal.menu.data.MenusRepository
import java.text.NumberFormat


class MenuEntryViewModel(private val itemsRepository: MenusRepository) : ViewModel() {

    var itemUiState by mutableStateOf(MenuUiState())
        private set

    fun updateUiState(itemDetails: MenuDetails) {
        itemUiState =
            MenuUiState(menuDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.menuDetails.toItem())
        }
    }

//    private fun validateInput(uiState: MenuDetails = itemUiState.menuDetails): Boolean {
//        return with(uiState) {
//            name.isNotBlank() && price.isNotBlank()  && description.isNotBlank() &&  price.toInt() >= 0
//        }
//    }

    private fun validateInput(uiState: MenuDetails = itemUiState.menuDetails): Boolean {
        return with(uiState) {
            val priceInput = price.trim()
            name.isNotBlank() &&
                    priceInput.isNotEmpty() &&
                    priceInput.toIntOrNull() != null &&
                    priceInput.toInt() > 0 &&
                    description.isNotBlank()
        }
    }

}

data class MenuUiState(
    val menuDetails: MenuDetails = MenuDetails(),
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

fun Menu.toItemUiState(isEntryValid: Boolean = false): MenuUiState = MenuUiState(
    menuDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Menu.toItemDetails(): MenuDetails = MenuDetails(
    id = id,
    name = name,
    price = price.toString(),
    description = description
)