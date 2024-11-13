package com.example.CleanUserFlow.data.database.local.entity

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val userId: Int,
    val username: String,
    val email: String,
    val budget: Double?,
    val preferences: String?,
    val password: String
)