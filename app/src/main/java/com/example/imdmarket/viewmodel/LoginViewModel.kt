package com.example.imdmarket.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class LoginViewModel : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")

    // Método de login
    fun login(navController: NavController) {
        if (username.value == "admin" && password.value == "admin") {
            navController.navigate("menu") // Navega para o menu
        } else {
            // Lógica para lidar com erro de login (alerta ou mensagem de erro)
        }
    }
}
