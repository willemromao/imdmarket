package com.example.imdmarket.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "IMD Market",  // Nome da aplicação
                        color = Color.White,  // Cor do texto
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF0F3C8D)  // Cor de fundo da barra
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Padding para o conteúdo abaixo da barra
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Campo para Login
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Login") },
                    modifier = Modifier.width(300.dp).padding(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x393F51B5),  // Cor do fundo do campo
                        focusedIndicatorColor = Color(0xFF3F51B5),  // Cor da linha ao focar
                        unfocusedIndicatorColor = Color.Gray, // Cor da linha quando não focado
                        disabledIndicatorColor = Color.LightGray,  // Cor da linha quando desabilitado
                        errorIndicatorColor = Color.Red   // Cor da linha quando em erro (se necessário)
                    )
                )

                // Campo para Senha
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    modifier = Modifier.width(300.dp).padding(8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x393F51B5),  // Cor do fundo do campo
                        focusedIndicatorColor = Color(0xFF3F51B5),  // Cor da linha ao focar
                        unfocusedIndicatorColor = Color.Gray, // Cor da linha quando não focado
                        disabledIndicatorColor = Color.LightGray,  // Cor da linha quando desabilitado
                        errorIndicatorColor = Color.Red   // Cor da linha quando em erro (se necessário)
                    )
                )


                // Botão Entrar
                Button(
                    onClick = { onLoginClick(username.text, password.text) },
                    modifier = Modifier.width(125.dp).padding(8.dp)
                ) {
                    Text("Entrar")
                }

                // Link para recuperação de senha
                TextButton(
                    onClick = { onForgotPasswordClick() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Esqueceu sua senha ou login?")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = { _, _ -> },
        onForgotPasswordClick = {}
    )
}