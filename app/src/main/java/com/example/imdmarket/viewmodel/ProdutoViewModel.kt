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

    fun carregarProdutos() {
        viewModelScope.launch {
            _produtos.value = repository.listarProdutos()
        }
    }

    fun atualizarProduto(produto: Produto): Boolean {
        val resultado = repository.atualizarProduto(produto)
        if (resultado > 0) {
            carregarProdutos()
            return true
        }
        return false
    }

    fun adicionarProduto(produto: Produto, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val produtoExistente = repository.buscarProdutoPorCodigo(produto.codigo)
            if (produtoExistente != null) {
                onResult(false, "Produto com este código já existe.")
            } else {
                val resultado = repository.inserirProduto(produto)
                if (resultado > 0) {
                    carregarProdutos()
                    onResult(true, null)
                } else {
                    onResult(false, "Erro ao cadastrar o produto. Tente novamente!")
                }
            }
        }
    }

    fun deletarProdutoPorCodigo(codigo: String): Boolean {
        val resultado = repository.deletarProdutoPorCodigo(codigo)
        if (resultado > 0) {
            carregarProdutos()
            return true
        }
        return false
    }

    fun buscarProdutoPorCodigo(codigo: String): Produto? {
        return repository.buscarProdutoPorCodigo(codigo)
    }
}
