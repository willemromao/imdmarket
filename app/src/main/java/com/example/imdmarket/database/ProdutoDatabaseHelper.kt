package com.example.imdmarket.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProdutoDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_PRODUTO (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CODIGO TEXT NOT NULL,
                $COLUMN_NOME TEXT NOT NULL,
                $COLUMN_DESCRICAO TEXT,
                $COLUMN_ESTOQUE INTEGER NOT NULL
            )
        """
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUTO")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "produtos.db"
        const val DATABASE_VERSION = 1
        const val TABLE_PRODUTO = "Produto"
        const val COLUMN_ID = "id"
        const val COLUMN_CODIGO = "codigo"
        const val COLUMN_NOME = "nome"
        const val COLUMN_DESCRICAO = "descricao"
        const val COLUMN_ESTOQUE = "estoque"
    }
}
