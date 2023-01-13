package com.example.pos_admin

import android.app.Application
import com.example.pos_admin.data.ShiftRoomDatabase

class ShiftApplication: Application() {
    val database: ShiftRoomDatabase by lazy { ShiftRoomDatabase.getDatabase(this) }
}