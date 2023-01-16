package com.example.pos_admin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="role")
    val role: String,
    @ColumnInfo(name="mail")
    val email: String
)