package com.example.pos_admin.repository


import com.example.pos_admin.data.Shift
import com.example.pos_admin.data.ShiftDao
import kotlinx.coroutines.flow.Flow


class ShiftRepository(private val shiftDao: ShiftDao) {
    val shifts = shiftDao.getShifts()
    fun retrieve(date: String, shift: Int): Flow<Shift> {
        return shiftDao.getShift(date, shift)
    }
    suspend fun insert(shift: Shift) {
        return shiftDao.insert(shift)
    }
    suspend fun update(shift: Shift) {
        return shiftDao.updateShift(shift)
    }
    suspend fun delete(shift: Shift) {
        return shiftDao.deleteShift(shift)
    }
}