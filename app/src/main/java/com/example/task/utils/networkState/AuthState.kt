package com.example.task.utils.networkState

import com.google.firebase.auth.FirebaseUser

data class AuthState(
    val data: FirebaseUser? = null,
    val error: String = "",
    val isLoading: Boolean = false
)