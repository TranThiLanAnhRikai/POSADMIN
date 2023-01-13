package com.example.pos_admin.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pos_admin.repository.ShiftRepository

class ShiftViewModelFactory(private val repository: ShiftRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShiftViewModel::class.java)){
            return ShiftViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model class")
    }
}
