package com.example.pos_admin.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pos_admin.data.User
import com.example.pos_admin.data.UserDao


class UsersViewModel(private val userDao: UserDao): ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputRole = MutableLiveData<String>()
    val inputCode = MutableLiveData<String>()
    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    fun insertNewUser() {

    }

}

class UsersViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
