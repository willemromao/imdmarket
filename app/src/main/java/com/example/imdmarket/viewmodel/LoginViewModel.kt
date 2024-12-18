package com.example.imdmarket.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.imdmarket.models.User
import com.example.imdmarket.repository.UserRepository
import com.example.imdmarket.navigation.Screen
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var username = mutableStateOf("")
    var password = mutableStateOf("")

    private val userRepository = UserRepository(application)

    var loginError = mutableStateOf<String?>(null)

    fun login(navController: NavController) {
        viewModelScope.launch {
            val user = userRepository.getUser(username.value)
            if (user != null && user.password == password.value) {
                navController.navigate(Screen.Menu.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            } else {
                loginError.value = "Credenciais inválidas."
            }
        }
    }

    fun resetCredentials(newUsername: String, newPassword: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val existingUser = userRepository.getUser(newUsername)
            if (existingUser != null) {
                val updatedRows = userRepository.updateUser(newUsername, newPassword)
                if (updatedRows > 0) {
                    onResult(true, null)
                } else {
                    onResult(false, "Erro ao atualizar as credenciais.")
                }
            } else {
                val newUser = User(username = newUsername, password = newPassword)
                val result = userRepository.addUser(newUser)
                if (result > 0) {
                    onResult(true, null)
                } else {
                    onResult(false, "Usuário já existe ou erro ao criar usuário.")
                }
            }
        }
    }
}
