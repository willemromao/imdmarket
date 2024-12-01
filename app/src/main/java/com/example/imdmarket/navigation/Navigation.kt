package com.example.imdmarket.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.imdmarket.ui.screens.AlterarProdutoScreen
import com.example.imdmarket.ui.screens.LoginScreen
import com.example.imdmarket.ui.screens.MenuScreen
import com.example.imdmarket.ui.screens.CadastroProdutoScreen
import com.example.imdmarket.ui.screens.DeletarProdutoScreen
import com.example.imdmarket.viewmodel.LoginViewModel


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route // Tela inicial
    ) {
        composable(route = Screen.Login.route) {
            // Passando o ViewModel para a LoginScreen
            LoginScreen(navController = navController, viewModel = LoginViewModel())
        }

        composable(route = Screen.Menu.route) {
            MenuScreen(navController = navController)
        }

        composable(route = Screen.CadastroProduto.route) {
            CadastroProdutoScreen(navController = navController)
        }

        composable(route = Screen.AlterarProduto.route) {
            AlterarProdutoScreen(navController = navController)
        }

        composable(route = Screen.DeletarProduto.route) {
            DeletarProdutoScreen(navController = navController)
        }

    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Menu : Screen("menu")
    object CadastroProduto : Screen("cadastro_produto")
    object AlterarProduto : Screen("alterar_produto")
    object DeletarProduto : Screen("deletar_produto")

}
