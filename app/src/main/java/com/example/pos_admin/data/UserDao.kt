package com.example.pos_admin.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)
    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): LiveData<User>
}