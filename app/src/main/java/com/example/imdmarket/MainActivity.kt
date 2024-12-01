package com.example.imdmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.imdmarket.ui.login.LoginScreen
import com.example.imdmarket.viewmodel.LoginViewModel
import com.example.imdmarket.ui.theme.IMDMarketTheme

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            IMDMarketTheme {
                LoginScreen(
                    onLoginClick = { username, password ->
                        loginViewModel.login(username, password,
                            onLoginSuccess = { /* Navegar para a próxima tela */ },
                            onLoginFailure = { errorMessage ->
                                // Exibir mensagem de erro, como um Toast
                            }
                        )
                    },
                    onForgotPasswordClick = {
                        loginViewModel.forgotPassword()
                    }
                )
            }
        }
    }
}
