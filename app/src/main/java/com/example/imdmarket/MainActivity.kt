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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val produtoViewModel: ProdutoViewModel = viewModel()
            App(produtoViewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(produtoViewModel: ProdutoViewModel) {
    val navController = rememberNavController()

    IMDMarketTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Navigation(navController, produtoViewModel)
        }
    }
}
