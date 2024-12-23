package com.example.imdmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.imdmarket.R
import com.example.imdmarket.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
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
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "O que deseja fazer?",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {navController.navigate(Screen.CadastroProduto.route) },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text(
                            text = "Cadastrar produto",
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = { navController.navigate(Screen.ListarProdutos.route) },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text(
                            text = "Listar produtos",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate(Screen.AlterarProduto.route) },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text(
                            text = "Alterar produto",
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = { navController.navigate(Screen.DeletarProduto.route) },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text(
                            text = "Deletar produto",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = R.drawable.market),
                    contentDescription = "Market",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(top = 16.dp)
                )
            }
        }
    )
}