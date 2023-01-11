package com.example.pos_admin.repository

import androidx.lifecycle.LiveData
import com.example.pos_admin.data.Shift
import com.example.pos_admin.data.ShiftDao
import java.util.concurrent.Flow

class ShiftRepository(private val shiftDao: ShiftDao) {
    val shifts = shiftDao.getShifts()
    fun retrieve(date: String) {
        return shiftDao.getShift(date)
    }
}