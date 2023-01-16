package com.example.pos_admin.data

import android.content.Context
import androidx.room.*
import com.example.pos_admin.Converters

@Database(entities = [Shift::class, MenuItem::class, User::class, Notification::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PosAdminRoomDatabase: RoomDatabase() {
    abstract fun shiftDao(): ShiftDao
    abstract fun userDao(): UserDao
    abstract fun notificationDao(): NotificationDao
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: PosAdminRoomDatabase? = null
        fun getDatabase(context: Context): PosAdminRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PosAdminRoomDatabase::class.java,
                    "pos_admin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}