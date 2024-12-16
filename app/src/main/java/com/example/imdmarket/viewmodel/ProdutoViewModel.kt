package com.example.imdmarket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdmarket.models.Produto
import com.example.imdmarket.repository.ProdutoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProdutoViewModel(private val repository: ProdutoRepository) : ViewModel() {

    private val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    val produtos: StateFlow<List<Produto>> get() = _produtos

    init {
        carregarProdutos()
    }

    // Carrega todos os produtos do repositório
    fun carregarProdutos() {
        viewModelScope.launch {
            _produtos.value = repository.listarProdutos()
        }
    }

    // Adiciona um produto
    fun adicionarProduto(produto: Produto): Boolean {
        val resultado = repository.inserirProduto(produto)
        if (resultado > 0) {
            carregarProdutos() // Atualiza a lista após adicionar
            return true
        }
        return false
    }

    // Atualiza um produto
    fun atualizarProduto(produto: Produto): Boolean {
        val resultado = repository.atualizarProduto(produto)
        if (resultado > 0) {
            carregarProdutos() // Atualiza a lista após alterar
            return true
        }
        return false
    }

    // Deleta um produto pelo código
    fun deletarProdutoPorCodigo(codigo: String): Boolean {
        val resultado = repository.deletarProdutoPorCodigo(codigo)
        if (resultado > 0) {
            carregarProdutos() // Atualiza a lista após deletar
            return true
        }
        return false
    }

    // Busca um produto pelo código
    fun buscarProdutoPorCodigo(codigo: String): Produto? {
        return repository.buscarProdutoPorCodigo(codigo)
    }
}
