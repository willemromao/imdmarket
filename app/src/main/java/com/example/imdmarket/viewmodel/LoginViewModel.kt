package com.example.imdmarket.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class LoginViewModel : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")

    // Método de login
    fun login(navController: NavController) {
        // Verifica se o usuário e senha são 'admin' para navegar para o menu
        if (username.value == "admin" && password.value == "admin") {
            navController.navigate("menu") // Navega para o menu
        } else {
            // Exemplo de lógica para lidar com erro de login (alerta ou mensagem de erro)
        }
    }
}
