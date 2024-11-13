package com.example.CleanUserFlow.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.CleanUserFlow.domain.usecase.GetUserUsecase
import com.example.CleanUserFlow.domain.models.User
import kotlinx.coroutines.launch

class UserViewModel(private val getUserUsecase: GetUserUsecase) : ViewModel(){
        fun loadUser(userId: Int,onResult: (User?) -> Unit){
            viewModelScope.launch {
                val users = getUserUsecase.getUser(userId)
                onResult(users)
            }
        }

        fun getUserByUsername(username: String, onResult: (User?) -> Unit) {
            viewModelScope.launch {
                val user = getUserUsecase.getUserByUsername(username)
                onResult(user)
            }
        }

        fun updateUser(user: User, onComplete: () -> Unit) {
            viewModelScope.launch {
                getUserUsecase.updateUser(user)
                onComplete()
            }
        }
        fun deleteUser(userId: Int,onComplete: () -> Unit){
            viewModelScope.launch {
                getUserUsecase.deleteUser(userId)
                onComplete()
                }
        }
        fun getAllUsers(onResult: (List<User>) -> Unit){
            viewModelScope.launch {
                val users = getUserUsecase.getAllUsers()
                onResult(users)
            }
        }
}