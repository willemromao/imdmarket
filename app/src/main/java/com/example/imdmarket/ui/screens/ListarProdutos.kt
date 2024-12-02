package com.example.imdmarket.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.imdmarket.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarProdutosScreen(navController: NavController) {
    // Exemplo de lista de produtos
    val produtos = listOf(
        Produto("001", "Produto A", "Descrição do produto A", 10),
        Produto("002", "Produto B", "Descrição do produto B", 20),
        Produto("003", "Produto C", "Descrição do produto C", 15),
        Produto("004", "Produto D", "Descrição do produto D", 35),
        Produto("005", "Produto E", "Descrição do produto E", 25),
        Produto("006", "Produto F", "Descrição do produto F", 60),
        Produto("007", "Produto G", "Descrição do produto G", 40)
    )

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

                // Lista de Produtos
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    items(produtos) { produto ->
                        ProdutoItem(produto)
                    }
                }

                // Spacer para empurrar o botão para o final
                Spacer(modifier = Modifier.weight(0.005f))

                // Botão Voltar
                Button(
                    onClick = {
                        navController.navigate(Screen.Menu.route) // Volta para o menu
                    },
                    modifier =
                        Modifier.width(125.dp).padding(vertical = 8.dp)
                ) {
                    Text("Voltar")
                }
            }
        }
    )
}

// Componente para exibir um produto
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

data class Produto(
    val codigo: String,
    val nome: String,
    val descricao: String,
    val estoque: Int
)

