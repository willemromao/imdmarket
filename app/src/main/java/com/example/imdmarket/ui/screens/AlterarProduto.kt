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
fun AlterarProdutoScreen(navController: NavController) {
    // Estados dos campos de texto
    var codigo by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var estoque by remember { mutableStateOf("") }

    // Contexto para o Toast
    val context = LocalContext.current

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
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Texto "ALTERAR PRODUTO"
                Text(
                    text = "ALTERAR PRODUTO",  // Título da tela
                    fontSize = 24.sp,  // Tamanho da fonte
                    fontWeight = FontWeight.Bold,  // Deixa o texto mais grosso
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

                // Campo Nome do Produto (Opcional)
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome do produto") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )

                // Campo Descrição do Produto (Opcional)
                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição do produto") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 8.dp),
                    maxLines = 5
                )

                // Campo Estoque (Opcional)
                OutlinedTextField(
                    value = estoque,
                    onValueChange = { estoque = it },
                    label = { Text("Estoque") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number // Apenas números
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botão Salvar
                Button(
                    onClick = {
                        // Validação dos campos
                        if (codigo.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Por favor, preencha o código do produto.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Produto alterado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(Screen.Menu.route)
                        }
                    },
                    modifier = Modifier.width(125.dp).padding(vertical = 8.dp)
                ) {
                    Text("Salvar")
                }
            }
        }
    )
}