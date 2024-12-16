package com.example.imdmarket

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.imdmarket.navigation.Navigation
import com.example.imdmarket.ui.theme.IMDMarketTheme
import com.example.imdmarket.viewmodel.ProdutoViewModel
import com.example.imdmarket.viewmodel.ProdutoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuração da interface do aplicativo
        setContent {
            // Inicializa o ProdutoViewModel usando o ProdutoViewModelFactory
            val produtoViewModel: ProdutoViewModel = viewModel(
                factory = ProdutoViewModelFactory(applicationContext)
            )
            // Passa o ProdutoViewModel para o App
            App(produtoViewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(produtoViewModel: ProdutoViewModel) {
    // Inicializa o controlador de navegação
    val navController = rememberNavController()

    // Configuração do tema e Scaffold principal
    IMDMarketTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            // Passa o ProdutoViewModel para o sistema de navegação
            Navigation(navController, produtoViewModel)
        }
    }
}
