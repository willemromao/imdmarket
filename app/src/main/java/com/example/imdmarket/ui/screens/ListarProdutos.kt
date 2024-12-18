package com.example.imdmarket.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.imdmarket.models.Produto
import com.example.imdmarket.navigation.Screen
import com.example.imdmarket.viewmodel.ProdutoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarProdutosScreen(navController: NavController, produtoViewModel: ProdutoViewModel) {
    val produtos by produtoViewModel.produtos.collectAsState(initial = emptyList())

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
                    text = "LISTA DE PRODUTOS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (produtos.isEmpty()) {
                    Text(
                        text = "Nenhum produto encontrado.",
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        items(produtos) { produto ->
                            ProdutoItem(produto)
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(0.005f))

                Button(
                    onClick = {
                        navController.navigate(Screen.Menu.route)
                    },
                    modifier = Modifier
                        .width(125.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text("Voltar")
                }
            }
        }
    )
}

@Composable
fun ProdutoItem(produto: Produto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Código: ${produto.codigo}",
                fontWeight = FontWeight.Bold
            )
            Text("Nome: ${produto.nome}")
            Text("Descrição: ${produto.descricao}")
            Text("Estoque: ${produto.estoque}")
        }
    }
}
