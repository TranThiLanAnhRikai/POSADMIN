package com.example.pos_admin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="shifts_database")
data class Shift(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="date")
    val date: String,
    @ColumnInfo(name="shift")
    val shift: Int
)