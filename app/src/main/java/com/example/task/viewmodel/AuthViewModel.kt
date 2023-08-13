package com.example.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.model.User
import com.example.task.repository.AuthRepository
import com.example.task.utils.Resource
import com.example.task.utils.networkState.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AuthViewModel
@Inject
constructor(
    private var authRepository: AuthRepository,
) : ViewModel() {

    private val userFlow = MutableStateFlow(AuthState())
    val user: StateFlow<AuthState> = userFlow

    ///Register viewmodel
    fun register(email: String, password: String, user: User) {
        authRepository.register(email, password, user).onEach {
            when (it) {
                is Resource.Loading -> {
                    userFlow.value = AuthState(isLoading = true)
                }

                is Resource.Error -> {
                    userFlow.value = AuthState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    userFlow.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    ///Login viewmodel
    fun login(email: String, password: String) {
        authRepository.login(email, password).onEach {
            when (it) {
                is Resource.Loading -> {
                    userFlow.value = AuthState(isLoading = true)
                }

                is Resource.Error -> {
                    userFlow.value = AuthState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    userFlow.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    ///Logged viewmodel
    fun loggedUser() {

        authRepository.getLoggedUser().onEach {
            when (it) {
                is Resource.Loading -> {
                    userFlow.value = AuthState(isLoading = true)
                }

                is Resource.Error -> {
                    userFlow.value = AuthState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    userFlow.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}
