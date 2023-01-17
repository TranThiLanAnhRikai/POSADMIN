package com.example.pos_admin.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(menuItem: MenuItem)
    @Update
    suspend fun update(menuItem: MenuItem)
    @Delete
    suspend fun delete(menuItem: MenuItem)
    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems(): LiveData<List<MenuItem>>
    @Query("SELECT * FROM menu_items WHERE id = :id")
    fun getMenuItem(id: Int): LiveData<MenuItem>
    @Query("SELECT img_path FROM menu_items WHERE id = :itemId")
    fun getImagePath(itemId: Int): String
}