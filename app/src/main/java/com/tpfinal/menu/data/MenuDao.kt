package com.tpfinal.menu.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * from menu ORDER BY name ASC")
    fun getAllItems(): Flow<List<Menu>>

    @Query("SELECT * from menu WHERE id = :id")
    fun getItem(id: Int): Flow<Menu>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(menu: Menu)

    @Update
    suspend fun update(menu: Menu)

    @Delete
    suspend fun delete(menu: Menu)
}