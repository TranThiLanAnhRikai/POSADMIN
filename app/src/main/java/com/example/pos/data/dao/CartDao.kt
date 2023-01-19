package com.example.pos.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pos.data.entity.Cart
import com.example.pos_admin.data.entity.MenuItem

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cart: Cart)
    @Update
    suspend fun update(cart: Cart)
    @Delete
    suspend fun delete(cart: Cart)
    @Delete
    suspend fun deleteAll()
    @Query("SELECT * FROM cart")
    fun getCarts(): LiveData<List<Cart>>
}