package com.example.CleanUserFlow.data.database.repositories

import com.example.CleanUserFlow.data.database.local.daos.UserDao
import com.example.CleanUserFlow.data.database.local.entity.UserEntity
import com.example.CleanUserFlow.domain.models.User
import kotlinx.coroutines.runBlocking

class UserRepository(private val userDao: UserDao) {

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)?.toDomainModel()
    }

    suspend fun getUser(id: Int): User? {
        return userDao.getUserById(id)?.toDomainModel()
    }

    suspend fun insertUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    suspend fun deleteUser(id: Int) {
        userDao.deleteUserById(id)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers().map { it.toDomainModel() }
    }

    fun updateUser(user: User) {
        runBlocking {
            userDao.insertUser(user.toEntity())
        }

    }
    // diğerlerini sonra yazarız
}

// Dönüşüm fonksiyonları
fun UserEntity.toDomainModel() = User(userId, username, email, budget, preferences, password)
fun User.toEntity() = UserEntity(userId, username, email, budget, preferences, password)
