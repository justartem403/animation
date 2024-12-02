package com.example.animation

class UserRepository(private val apiService: UserApiService) {
    suspend fun fetchUsers(): List<User> = apiService.getUsers()
}