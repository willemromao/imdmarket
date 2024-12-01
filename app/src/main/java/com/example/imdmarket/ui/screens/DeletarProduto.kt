package com.example.imdmarket.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.imdmarket.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletarProdutoScreen(navController: NavController) {
    // Estado do campo de código do produto
    var codigo by remember { mutableStateOf("") }

    // Contexto para o Toast
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "IMD Market",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
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
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "DELETAR PRODUTO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Campo Código do Produto (Obrigatório)
                OutlinedTextField(
                    value = codigo,
                    onValueChange = { codigo = it },
                    label = { Text("Código do produto") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number // Apenas números
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botão Deletar
                Button(
                    onClick = {
                        // Validação do código
                        if (codigo.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Por favor, preencha o código do produto.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Produto deletado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(Screen.Menu.route)
                        }
                    },
                    modifier = Modifier.width(125.dp).padding(vertical = 8.dp)
                ) {
                    Text("Deletar")
                }
            }
        }
    )
}
