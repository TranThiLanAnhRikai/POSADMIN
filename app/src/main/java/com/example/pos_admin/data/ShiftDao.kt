package com.example.pos_admin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShiftDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shift: Shift)
    @Update
    suspend fun update(shift: Shift)
    @Delete
    suspend fun delete(shift: Shift)
    @Query("SELECT * FROM shifts")
    fun getShifts(): Flow<List<Shift>>
    @Query("SELECT * FROM shifts WHERE date = :date AND shift_time = :shift")
    fun getShift(date: String, shift: Int): Flow<Shift>
}