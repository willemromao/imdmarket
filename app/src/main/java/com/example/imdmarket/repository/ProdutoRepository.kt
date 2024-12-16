package com.example.imdmarket.repository

import android.content.ContentValues
import android.content.Context
import com.example.imdmarket.database.ProdutoDatabaseHelper
import com.example.imdmarket.models.Produto

class ProdutoRepository(context: Context) {

    private val dbHelper = ProdutoDatabaseHelper(context)

    // Insere um produto no banco de dados
    fun inserirProduto(produto: Produto): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("codigo", produto.codigo)
            put("nome", produto.nome)
            put("descricao", produto.descricao)
            put("estoque", produto.estoque)
        }
        return db.insert("Produto", null, values)
    }

    // Lista todos os produtos do banco de dados
    fun listarProdutos(): List<Produto> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "Produto", null, null, null, null, null, null
        )

        val produtos = mutableListOf<Produto>()
        with(cursor) {
            while (moveToNext()) {
                produtos.add(
                    Produto(
                        id = getInt(getColumnIndexOrThrow("id")),
                        codigo = getString(getColumnIndexOrThrow("codigo")),
                        nome = getString(getColumnIndexOrThrow("nome")),
                        descricao = getString(getColumnIndexOrThrow("descricao")),
                        estoque = getInt(getColumnIndexOrThrow("estoque"))
                    )
                )
            }
            close()
        }
        return produtos
    }

    // Atualiza um produto no banco de dados
    fun atualizarProduto(produto: Produto): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("codigo", produto.codigo)
            put("nome", produto.nome)
            put("descricao", produto.descricao)
            put("estoque", produto.estoque)
        }
        return db.update(
            "Produto",
            values,
            "codigo = ?",
            arrayOf(produto.codigo)
        )
    }

    // Deleta um produto pelo código
    fun deletarProdutoPorCodigo(codigo: String): Int {
        val db = dbHelper.writableDatabase
        return db.delete(
            "Produto",
            "codigo = ?",
            arrayOf(codigo)
        )
    }

    // Busca um produto pelo código
    fun buscarProdutoPorCodigo(codigo: String): Produto? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "Produto",
            null,
            "codigo = ?",
            arrayOf(codigo),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val produto = Produto(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo")),
                nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao")),
                estoque = cursor.getInt(cursor.getColumnIndexOrThrow("estoque"))
            )
            cursor.close()
            return produto
        }
        cursor.close()
        return null
    }
}
