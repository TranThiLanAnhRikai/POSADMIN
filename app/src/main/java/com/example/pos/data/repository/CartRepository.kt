package com.example.pos.data.repository

import androidx.lifecycle.LiveData
import com.example.pos.data.dao.CartDao
import com.example.pos.data.entity.Cart


class CartRepository(private val cartDao: CartDao) {
    val carts = cartDao.getCarts()


    suspend fun insert(cart: Cart) {
        return cartDao.insert(cart)
    }

    suspend fun update(cart: Cart) {
        return cartDao.update(cart)
    }

    suspend fun delete(cart: Cart) {
        return cartDao.delete(cart)
    }
    suspend fun deleteAll() {
        return cartDao.deleteAll()
    }

}