package com.example.pos_admin.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pos_admin.const.Destination
import com.example.pos_admin.const.Role
import com.example.pos_admin.data.entity.User
import com.example.pos_admin.data.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {
    val firstLoginCode = MutableLiveData<String>()
    var user: User? = null
    fun isFirstLoginValid(): Boolean {

        firstLoginCode.value?.let {
            user = userRepository.getUser(it).value
            if (user == null) {
                return false
            }
        }
        return true
    }

    fun nextFragment(): Destination {
        if (user != null && user?.role == Role.STAFF.roleName) {
            return Destination.STAFF
        }
        else {
            return Destination.NON_STAFF
        }
    }
}

class LoginViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}