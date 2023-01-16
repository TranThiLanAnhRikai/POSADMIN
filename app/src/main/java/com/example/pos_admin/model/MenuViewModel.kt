package com.example.pos_admin.model

import androidx.lifecycle.*
import com.example.pos_admin.data.MenuItemDao
import com.example.pos_admin.data.ShiftDao
import java.text.NumberFormat

class MenuViewModel(private val menuItemDao: MenuItemDao): ViewModel() {
    val name = MutableLiveData<String>()
    val type = MutableLiveData<Int>()
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

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
