package com.example.pos_admin.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notification: Notification)
    @Update
    suspend fun update(notification: Notification)
    @Delete
    suspend fun delete(notification: Notification)
    @Query("SELECT * FROM notifications")
    fun getAllNotifications(): LiveData<List<Notification>>
    @Query("SELECT * FROM notifications WHERE id = :id")
    fun getNotification(id: Int): LiveData<Notification>
}