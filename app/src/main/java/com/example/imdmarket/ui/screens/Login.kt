package com.example.imdmarket.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.imdmarket.viewmodel.LoginViewModel
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.imdmarket.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    var username by remember { mutableStateOf(viewModel.username.value) }
    var password by remember { mutableStateOf(viewModel.password.value) }

    viewModel.username.value = username
    viewModel.password.value = password

    val context = LocalContext.current

    val loginError = viewModel.loginError.value
    if (loginError != null) {
        LaunchedEffect(loginError) {
            Toast.makeText(context, loginError, Toast.LENGTH_SHORT).show()
            viewModel.loginError.value = null
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "IMD Market",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF0F3C8D)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Login") },
                    modifier = Modifier.width(300.dp).padding(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x393F51B5),
                        focusedIndicatorColor = Color(0xFF3F51B5),
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.LightGray,
                        errorIndicatorColor = Color.Red
                    )
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    modifier = Modifier.width(300.dp).padding(8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x393F51B5),
                        focusedIndicatorColor = Color(0xFF3F51B5),
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.LightGray,
                        errorIndicatorColor = Color.Red
                    )
                )

                Button(
                    onClick = {
                        viewModel.login(navController)
                    },
                    modifier = Modifier.width(125.dp).padding(8.dp)
                ) {
                    Text("Entrar")
                }

                TextButton(
                    onClick = { navController.navigate(Screen.RecoverPassword.route) },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Esqueci minha senha/login")
                }
            }
        }
    )
}
