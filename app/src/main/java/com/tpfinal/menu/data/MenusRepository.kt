package com.tpfinal.menu.data

import kotlinx.coroutines.flow.Flow

interface MenusRepository {
    fun getAllItemsStream(): Flow<List<Menu>>

    fun getItemStream(id: Int): Flow<Menu?>

    suspend fun insertItem(menu: Menu)

    suspend fun deleteItem(menu: Menu)

    suspend fun updateItem(menu: Menu)
}