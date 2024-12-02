package com.example.animation

sealed class Screen(val route: String) {
    object UserList : Screen("userList")
    object UserDetail : Screen("userDetail/{userId}") {
        fun createRoute(userId: Int) = "userDetail/$userId"
    }
}