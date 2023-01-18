package com.example.pos_admin.data.repository

import com.example.pos_admin.data.dao.UserDao
import com.example.pos_admin.data.entity.User

/*Repository for user class */

class UserRepository(private val userDao: UserDao) {

        val users = userDao.getAllUsers()

        suspend fun insert(user: User) {
            return userDao.insert(user)
        }

        suspend fun update(user: User) {
            return userDao.update(user)
        }

        suspend fun delete(user: User) {
            return userDao.delete(user)
        }

}