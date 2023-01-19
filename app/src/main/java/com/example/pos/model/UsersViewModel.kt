package com.example.pos_admin.model

import androidx.lifecycle.*
import com.example.pos_admin.data.entity.User
import com.example.pos_admin.data.repository.UserRepository
import kotlinx.coroutines.launch


class UsersViewModel(private val userRepository: UserRepository): ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputRole = MutableLiveData<String>()
    val firstCode = MutableLiveData<String>()
    val secondCode = MutableLiveData<String>()
    fun getAllUsers(): LiveData<List<User>> {
        return userRepository.users
    }

    fun insertNewUser() {
        viewModelScope.launch {
            userRepository.insert(User(0, inputName.value!!, inputRole.value!!, firstCode.value!!, "0"))
            inputName.value = ""
            firstCode.value = ""
        }
    }

}

class UsersViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
