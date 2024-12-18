package com.tpfinal.menu.data

import kotlinx.coroutines.flow.Flow

class OfflineMenuRepository(private val menuDao: MenuDao) : MenusRepository {
    override fun getAllItemsStream(): Flow<List<Menu>> = menuDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Menu?> = menuDao.getItem(id)

    override suspend fun insertItem(menu: Menu) = menuDao.insert(menu)

    override suspend fun deleteItem(menu: Menu) = menuDao.delete(menu)

    override suspend fun updateItem(menu: Menu) = menuDao.update(menu)
}