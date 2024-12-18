package com.example.imdmarket.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.imdmarket.ui.screens.*
import com.example.imdmarket.viewmodel.LoginViewModel
import com.example.imdmarket.viewmodel.ProdutoViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    produtoViewModel: ProdutoViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(navController = navController, viewModel = loginViewModel)
        }
        composable(Screen.Menu.route) {
            MenuScreen(navController = navController)
        }
        composable(Screen.CadastroProduto.route) {
            CadastroProdutoScreen(navController = navController, produtoViewModel = produtoViewModel)
        }
        composable(Screen.AlterarProduto.route) {
            AlterarProdutoScreen(navController = navController, produtoViewModel = produtoViewModel)
        }
        composable(Screen.DeletarProduto.route) {
            DeletarProdutoScreen(navController = navController, produtoViewModel = produtoViewModel)
        }
        composable(Screen.ListarProdutos.route) {
            ListarProdutosScreen(navController = navController, produtoViewModel = produtoViewModel)
        }
        composable(Screen.RecoverPassword.route) { // Nova rota
            val loginViewModel: LoginViewModel = viewModel()
            RecoverPasswordScreen(navController = navController, viewModel = loginViewModel)
        }
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Menu : Screen("menu")
    object CadastroProduto : Screen("cadastro_produto")
    object AlterarProduto : Screen("alterar_produto")
    object DeletarProduto : Screen("deletar_produto")
    object ListarProdutos : Screen("listar_produtos")
    object RecoverPassword : Screen("recover_password")
}
