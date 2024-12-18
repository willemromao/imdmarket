package com.example.imdmarket.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.imdmarket.navigation.Screen
import com.example.imdmarket.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverPasswordScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    var username by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recuperar Senha",
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
                    label = { Text("Nome de Usuário") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x393F51B5),
                        focusedIndicatorColor = Color(0xFF3F51B5),
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.LightGray,
                        errorIndicatorColor = Color.Red
                    )
                )

                TextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Nova Senha") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x393F51B5),
                        focusedIndicatorColor = Color(0xFF3F51B5),
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.LightGray,
                        errorIndicatorColor = Color.Red
                    )
                )

                TextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmar Nova Senha") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x393F51B5),
                        focusedIndicatorColor = Color(0xFF3F51B5),
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.LightGray,
                        errorIndicatorColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = {
                            if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Por favor, preencha todos os campos.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            if (newPassword != confirmPassword) {
                                Toast.makeText(
                                    context,
                                    "As senhas não coincidem.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            // Chama a função resetCredentials no ViewModel
                            viewModel.resetCredentials(username, newPassword) { success, message ->
                                if (success) {
                                    Toast.makeText(
                                        context,
                                        "Credenciais atualizadas com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                } else {
                                    val errorMessage = message ?: "Erro ao atualizar as credenciais."
                                    Toast.makeText(
                                        context,
                                        errorMessage,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(125.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text("Atualizar")
                    }

                    Button(
                        onClick = {
                            // Navega de volta para a tela de login
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .width(125.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    )
}
