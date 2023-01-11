package com.example.pos_admin.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pos_admin.data.Shift
import com.example.pos_admin.repository.ShiftRepository
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

class ShiftViewModel(private val repository: ShiftRepository) : ViewModel() {

    // LiveData to hold the current shifts
    val shifts: Flow<List<Shift>> = repository.shifts

    // function to insert a new Shift
    suspend fun insert(shift: Shift) {
        repository.insert(shift)
    }

    // function to delete a Shift
    suspend fun delete(shift: Shift) {
        repository.delete(shift)
    }

    // function to update a Shift
    suspend fun update(shift: Shift) {
        repository.update(shift)
    }
}


//    private val calendar: Calendar = Calendar.getInstance()
//    private val currentDateTime: Date = calendar.time
//
//    private val dateFormat = SimpleDateFormat("EEEE, yyyy/MM/dd")
//    val formattedDateTime: String = dateFormat.format(currentDateTime)
//
//    fun isFirstLoginValid(): Boolean {
//        return true
//    }
//
//    fun isSecondLoginValid(): Boolean {
//        return true
//    }




