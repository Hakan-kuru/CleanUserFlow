package com.example.CleanUserFlow.data.database.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.CleanUserFlow.data.database.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("SELECT * FROM user_table WHERE userId = :userId")
    suspend fun getUserById(userId: Int): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM user_table WHERE password = :password")
    suspend fun getUserByPassword(password: String): UserEntity?

    @Query("DELETE FROM user_table WHERE userId = :userId")
    suspend fun deleteUserById(userId: Int)

    @Query("SELECT* FROM user_table ")
    suspend fun getAllUsers(): List<UserEntity>

}