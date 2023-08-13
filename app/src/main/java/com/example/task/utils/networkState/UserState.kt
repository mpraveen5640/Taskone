package com.example.task.utils.networkState

import com.example.task.model.User


data class UserState(
    val data: User? = null,
    val error: String = "",
    val isLoading: Boolean = false
)