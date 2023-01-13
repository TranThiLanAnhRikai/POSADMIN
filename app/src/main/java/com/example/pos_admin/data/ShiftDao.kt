package com.example.pos_admin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.LiveData

@Dao
interface ShiftDAO {
    @Query("SELECT * FROM shifts_table")
    fun getShifts(): Flow<List<Shift>>
    @Query("SELECT * FROM shifts_table WHERE date = :date AND shift_time = :shiftTime")
    fun getShift(date: String, shiftTime: Int): Flow<Shift>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShift(shift: Shift): Long
    @Update
    suspend fun updateShift(shift: Shift): Int
    @Delete
    suspend fun deleteShift(shift: Shift): Int
}