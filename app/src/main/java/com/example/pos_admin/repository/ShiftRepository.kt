package com.example.pos_admin.repository


import com.example.pos_admin.data.Shift
import com.example.pos_admin.data.ShiftDAO
import kotlinx.coroutines.flow.Flow


class ShiftRepository(private val shiftDao: ShiftDAO) {
    val shifts = shiftDao.getShifts()
    fun retrieve(date: String, shift: Int): Flow<Shift> {
        return shiftDao.getShift(date, shift)
    }
    suspend fun insert(shift: Shift): Long {
        return shiftDao.insertShift(shift)
    }
    suspend fun update(shift: Shift): Int {
        return shiftDao.updateShift(shift)
    }
    suspend fun delete(shift: Shift): Int {
        return shiftDao.deleteShift(shift)
    }
}