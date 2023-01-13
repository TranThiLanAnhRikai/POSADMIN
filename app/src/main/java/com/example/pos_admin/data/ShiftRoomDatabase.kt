package com.example.pos_admin.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.runBlocking

@Database(entities = [Shift::class], version = 1)
abstract class ShiftRoomDatabase: RoomDatabase() {
    abstract val shiftDAO: ShiftDAO

    companion object {
        @Volatile
        private var INSTANCE: ShiftRoomDatabase? = null

        fun getInstance(context: Context): ShiftRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShiftRoomDatabase::class.java,
                        "shifts_database"
                    )
                        .build()
                }
                return instance
            }
        }
    }
}

/*
class ShiftApplication: Application() {
    val database: ShiftRoomDatabase by lazy { ShiftRoomDatabase.getDatabase(this)}
*/
/*    override fun onCreate() {
        super.onCreate()
        runBlocking {
            database.shiftDao().insert(Shift(0, "Anh", "13", 1))
        }

    }*//*

}
*/
