package com.example.pos_admin.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pos_admin.data.MenuItemDao
import com.example.pos_admin.data.ShiftDao

class MenuViewModel(private val menuItemDao: MenuItemDao): ViewModel() {
}

class MenuViewModelFactory(private val menuItemDao: MenuItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MenuViewModel(menuItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
