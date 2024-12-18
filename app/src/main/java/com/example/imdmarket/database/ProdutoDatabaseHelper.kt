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
                $COLUMN_CODIGO TEXT NOT NULL UNIQUE,
                $COLUMN_NOME TEXT NOT NULL,
                $COLUMN_DESCRICAO TEXT,
                $COLUMN_ESTOQUE INTEGER NOT NULL
            )
        """
        db.execSQL(CREATE_TABLE)

        val CREATE_TABLE_USER = """
            CREATE TABLE $TABLE_USER (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT NOT NULL UNIQUE,
                $COLUMN_PASSWORD TEXT NOT NULL
            )
        """
        db.execSQL(CREATE_TABLE_USER)

        val insertAdmin = """
            INSERT INTO $TABLE_USER ($COLUMN_USERNAME, $COLUMN_PASSWORD)
            VALUES ('admin', 'admin')
        """
        db.execSQL(insertAdmin)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUTO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "imdmarket.db"
        private const val DATABASE_VERSION = 3

        const val TABLE_PRODUTO = "Produto"
        const val COLUMN_ID = "id"
        const val COLUMN_CODIGO = "codigo"
        const val COLUMN_NOME = "nome"
        const val COLUMN_DESCRICAO = "descricao"
        const val COLUMN_ESTOQUE = "estoque"

        const val TABLE_USER = "User"
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }
}
