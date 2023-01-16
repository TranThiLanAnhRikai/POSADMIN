package com.example.pos_admin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: Int,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "img_src")
    val image: String
)
