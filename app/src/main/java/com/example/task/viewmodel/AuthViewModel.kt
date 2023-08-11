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

    private val _user = MutableStateFlow(AuthState())
    val user: StateFlow<AuthState> = _user

    fun login(email: String, password: String) {
        authRepository.login(email, password).onEach {
            when (it) {
                is Resource.Loading -> {
                    _user.value = AuthState(isLoading = true)
                }

                is Resource.Error -> {
                    _user.value = AuthState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    _user.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun register(email: String, password: String, user: User) {
        authRepository.register(email, password, user).onEach {
            when (it) {
                is Resource.Loading -> {
                    _user.value = AuthState(isLoading = true)
                }

                is Resource.Error -> {
                    _user.value = AuthState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    _user.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}
