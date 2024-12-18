package com.example.imdmarket.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.example.imdmarket.database.ProdutoDatabaseHelper
import com.example.imdmarket.models.User

class UserRepository(context: Context) {

    private val dbHelper = ProdutoDatabaseHelper(context)

    fun getUser(username: String): User? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            ProdutoDatabaseHelper.TABLE_USER,
            null,
            "${ProdutoDatabaseHelper.COLUMN_USERNAME} = ?",
            arrayOf(username),
            null,
            null,
            null
        )
        var user: User? = null
        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(ProdutoDatabaseHelper.COLUMN_USER_ID)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(ProdutoDatabaseHelper.COLUMN_USERNAME)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(ProdutoDatabaseHelper.COLUMN_PASSWORD))
            )
        }
        cursor.close()
        return user
    }

    fun addUser(user: User): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ProdutoDatabaseHelper.COLUMN_USERNAME, user.username)
            put(ProdutoDatabaseHelper.COLUMN_PASSWORD, user.password)
        }
        return try {
            db.insertOrThrow(ProdutoDatabaseHelper.TABLE_USER, null, values)
        } catch (e: SQLiteConstraintException) {
            -1L
        } catch (e: Exception) {
            -1L
        }
    }

    fun updateUser(username: String, newPassword: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ProdutoDatabaseHelper.COLUMN_PASSWORD, newPassword)
        }
        return db.update(
            ProdutoDatabaseHelper.TABLE_USER,
            values,
            "${ProdutoDatabaseHelper.COLUMN_USERNAME} = ?",
            arrayOf(username)
        )
    }
}
