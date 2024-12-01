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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.imdmarket.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
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
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "O que deseja fazer?",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 24.sp,  // Aumenta o tamanho da fonte
                        fontWeight = FontWeight.Bold  // Deixa o texto mais grosso
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Primeira linha de botões
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly // Para distribuir os botões igualmente
                ) {
                    Button(
                        onClick = { /* Lógica para cadastrar produto */ },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text("Cadastrar produto")
                    }

                    Button(
                        onClick = { /* Lógica para listar produtos */ },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text("Listar produtos")
                    }
                }

                // Segunda linha de botões
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly // Para distribuir os botões igualmente
                ) {
                    Button(
                        onClick = { /* Lógica para alterar produto */ },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text("Alterar produto")
                    }

                    Button(
                        onClick = { /* Lógica para deletar produto */ },
                        modifier = Modifier.width(150.dp).padding(8.dp)
                    ) {
                        Text("Deletar produto")
                    }
                }

                Spacer(modifier = Modifier.height(32.dp)) // Espaçamento entre os botões e a imagem
                Image(
                    painter = painterResource(id = R.drawable.market), // Substitua 'logo' pelo nome do seu arquivo de imagem
                    contentDescription = "Market", // Descrição alternativa da imagem
                    modifier = Modifier
                        .size(150.dp)  // Ajuste o tamanho da imagem conforme necessário
                        .padding(top = 16.dp)  // Padding opcional para ajustar a posição
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {
    // Para o preview, você pode passar um controller falso, já que ele não será usado no preview
    MenuScreen(navController = rememberNavController())
}