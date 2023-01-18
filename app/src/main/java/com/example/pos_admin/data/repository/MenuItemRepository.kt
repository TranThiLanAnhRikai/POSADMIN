package com.example.pos_admin.data.repository

import androidx.lifecycle.LiveData
import com.example.pos_admin.data.dao.MenuItemDao
import com.example.pos_admin.data.entity.MenuItem


/*Repository for menu_item class */

class MenuItemRepository(private val menuItemDao: MenuItemDao) {

    val items = menuItemDao.getAllMenuItems()

    fun getMenuItem(id: Int): LiveData<MenuItem> {
        return menuItemDao.getMenuItem(id)
    }

    suspend fun insert(menuItem: MenuItem) {
        return menuItemDao.insert(menuItem)
    }

    suspend fun update(menuItem: MenuItem) {
        return menuItemDao.update(menuItem)
    }

    suspend fun delete(menuItem: MenuItem) {
        return menuItemDao.delete(menuItem)
    }

}