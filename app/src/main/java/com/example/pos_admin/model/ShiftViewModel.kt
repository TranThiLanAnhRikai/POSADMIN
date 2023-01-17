package com.example.pos_admin.model

import android.content.ContentValues.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pos_admin.data.Shift
import com.example.pos_admin.data.ShiftDao
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log
import androidx.lifecycle.MutableLiveData

class ShiftViewModel(private val shiftDao: ShiftDao): ViewModel() {

    val inputName = MutableLiveData<String>()
    val _date = MutableLiveData<String>()
    val _shift = MutableLiveData<Int>()

    fun getAllShifts(): LiveData<List<Shift>> {
            return shiftDao.getAllShifts()
    }

   fun insertShift() {
        val name = inputName.value!!
        viewModelScope.launch {
            shiftDao.insert(Shift(0, name, _date.value!!, _shift.value!!))
        }
       inputName.value = ""
       Log.d(TAG, "name $inputName.value")

    }

    fun getShifts(date: String, shift: Int): LiveData<List<Shift>> {
        return shiftDao.getShifts(date, shift)
    }

    private val calendar: Calendar = Calendar.getInstance()
    private val currentDateTime: Date = calendar.time

    private val dateFormat = SimpleDateFormat("EEEE, yyyy/MM/dd")
    val formattedDateTime: String = dateFormat.format(currentDateTime)

    fun isFirstLoginValid(): Boolean {
        return true
    }

    fun isSecondLoginValid(): Boolean {
        return true
    }


}

class ShiftViewModelFactory(private val shiftDao: ShiftDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShiftViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShiftViewModel(shiftDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
