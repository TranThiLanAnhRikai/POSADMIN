package com.example.pos_admin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="shifts_table")
data class Shift(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int,
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="date")
    var date: String,
    @ColumnInfo(name="shift_time")
    var shiftTime: Int
)