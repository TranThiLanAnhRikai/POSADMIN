package com.example.pos_admin.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.pos_admin.data.Shift
import com.example.pos_admin.repository.ShiftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ShiftViewModel(private val repository: ShiftRepository) : ViewModel() {
    val name = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val shift = MutableLiveData<Int>()

    fun insert() {
        val shiftname = name.value!!
        val shiftDate = date.value!!
        val shift = shift.value!!
        viewModelScope.launch {
            insert( Shift(0, shiftname, shiftDate, shift))
            Log.d(TAG, "shift submitted")
        }
    }
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




