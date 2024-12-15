package com.example.imdmarket.repository

import android.content.Context
import android.preference.PreferenceManager
import com.example.imdmarket.models.Produto
import com.google.gson.Gson

class SharedPreferencesUtils {
    val listaProdutos = mutableSetOf<Produto>()

    fun salvarProdutos(context: Context, produtos: Set<Produto>) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()

        val produtosJson = Gson().toJson(produtos)
        editor.putString("produtos", produtosJson)
        editor.apply()
    }

    fun carregarProdutos(context: Context): Set<Produto> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val produtosJson = sharedPreferences.getString("produtos", "[]") ?: "[]"
        return Gson().fromJson(produtosJson, Array<Produto>::class.java).toSet()
    }
}