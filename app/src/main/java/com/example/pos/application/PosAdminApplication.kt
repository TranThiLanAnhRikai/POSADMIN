package com.example.pos_admin.application

import android.app.Application
import com.example.pos_admin.data.PosAdminRoomDatabase

class PosAdminApplication: Application() {

    companion object {
        lateinit var instance: PosAdminApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    val database: PosAdminRoomDatabase by lazy { PosAdminRoomDatabase.getDatabase(this) }
}

