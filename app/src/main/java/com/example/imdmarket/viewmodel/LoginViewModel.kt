package com.example.imdmarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun login(username: String, password: String, onLoginSuccess: () -> Unit, onLoginFailure: (String) -> Unit) {
        viewModelScope.launch {
            // Lógica para autenticar o usuário
            if (username == "admin" && password == "admin") {
                onLoginSuccess()
            } else {
                onLoginFailure("Login ou senha inválidos")
            }
        }
    }

    fun forgotPassword() {
        // Lógica para recuperação de senha
    }
}
