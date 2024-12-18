package com.example.imdmarket.models

data class Produto(
    val id: Int? = null,
    val codigo: String,
    val nome: String,
    val descricao: String,
    val estoque: Int
)
