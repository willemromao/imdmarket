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
import com.example.imdmarket.models.Produto
import com.example.imdmarket.navigation.Screen
import com.example.imdmarket.viewmodel.ProdutoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroProdutoScreen(
    navController: NavController,
    produtoViewModel: ProdutoViewModel
) {
    var codigo by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var estoque by remember { mutableStateOf("") }

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
                    text = "CADASTRAR PRODUTO",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = codigo,
                    onValueChange = { codigo = it },
                    label = { Text("Código do produto") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome do produto") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

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

                OutlinedTextField(
                    value = estoque,
                    onValueChange = { estoque = it },
                    label = { Text("Estoque") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = {
                            if (codigo.isEmpty() || nome.isEmpty() || descricao.isEmpty() || estoque.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Por favor, preencha todos os campos.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            val estoqueInt = estoque.toIntOrNull()
                            if (estoqueInt == null || estoqueInt < 0) {
                                Toast.makeText(
                                    context,
                                    "Por favor, insira um valor numérico válido para o estoque.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            val novoProduto = Produto(
                                codigoProduto = codigo,
                                nomeProduto = nome,
                                descricaoProduto = descricao,
                                estoque = estoqueInt
                            )

                            val sucesso = produtoViewModel.adicionarProduto(novoProduto)
                            if (sucesso) {
                                Toast.makeText(
                                    context,
                                    "Produto cadastrado com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate(Screen.Menu.route)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Produto já existe!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .width(125.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text("Salvar")
                    }

                    Button(
                        onClick = {
                            codigo = ""
                            nome = ""
                            descricao = ""
                            estoque = ""
                        },
                        modifier = Modifier
                            .width(125.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        Text("Limpar")
                    }
                }
            }
        }
    )
}
