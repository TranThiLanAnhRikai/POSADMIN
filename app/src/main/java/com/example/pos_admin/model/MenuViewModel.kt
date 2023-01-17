package com.example.pos_admin.model

import android.widget.Toast
import androidx.lifecycle.*
import com.example.pos_admin.data.MenuItem
import com.example.pos_admin.data.MenuItemDao
import kotlinx.coroutines.launch
import java.text.NumberFormat


class MenuViewModel(private val menuItemDao: MenuItemDao): ViewModel() {
    val name = MutableLiveData<String>()
    val type = MutableLiveData<String>()
    val image = MutableLiveData<String>()
    val _price = MutableLiveData<String>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun insertItem() {
        viewModelScope.launch {
            menuItemDao.insert(MenuItem(0, name.value!!, type.value!!, _price.value!!, image.value!!))
        }
        name.value = ""
        type.value = ""
        image.value = ""
        _price.value = ""

    }

    fun getAllMenuItems(): LiveData<List<MenuItem>> {
        return menuItemDao.getAllMenuItems()
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
