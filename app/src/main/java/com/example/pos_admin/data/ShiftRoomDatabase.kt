package com.example.pos_admin.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Shift::class], version = 1, exportSchema = false)
abstract class ShiftRoomDatabase: RoomDatabase() {
    abstract fun shiftDao(): ShiftDao
    companion object {
        @Volatile
        private var INSTANCE: ShiftRoomDatabase? = null

        fun getDatabase(context: Context): ShiftRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShiftRoomDatabase::class.java,
                    "shifts_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}

class ShiftApplication: Application() {
    val database: ShiftRoomDatabase by lazy { ShiftRoomDatabase.getDatabase(this)}
}