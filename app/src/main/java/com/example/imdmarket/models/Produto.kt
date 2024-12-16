package com.example.imdmarket.models

data class Produto(
    val id: Int? = null, // ID opcional, pois será gerado automaticamente no SQLite
    val codigo: String,
    val nome: String,
    val descricao: String,
    val estoque: Int
)
