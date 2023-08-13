package com.example.task.utils

object RegisterUtils {
    private val existinguser = listOf("java", "android")

    fun validateRegisterInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            return false
        }
        if (username in existinguser) {
            return false
        }
        if (password != confirmPassword) {
            return false
        }
        return true
    }
}