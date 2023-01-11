package com.example.pos_admin.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pos_admin.repository.ShiftRepository

class ShiftViewModelFactory(private val repository: ShiftRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShiftViewModel(repository) as T
    }
}
