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
    @Query("SELECT * FROM shifts_database")
    fun getShifts(): Flow<List<Shift>>
    @Query("SELECT * FROM shifts_database WHERE date = :date")
    fun getShift(date: String): Flow<Shift>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shift: Shift)
    @Update
    suspend fun updateShift(shift: Shift)
    @Delete
    suspend fun deleteShift(shift: Shift)
}