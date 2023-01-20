package com.example.pos.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pos.data.entity.CartItem

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartItem: CartItem)
    @Update
    suspend fun update(cartItem: CartItem)
    @Delete
    suspend fun delete(cartItem: CartItem)
    @Delete
    suspend fun deleteAll()
    @Query("SELECT * FROM cart WHERE oder_number = :oderNumber")
    fun getCart(oderNumber: Int): LiveData<List<CartItem>>
}