package com.example.applogin.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.applogin.Model.Login
import java.util.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertLogin(login: Login): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.LoginEntry.COLUMN_USER_ID, login.id)
        values.put(DBContract.LoginEntry.EMAIL, login.email)
        values.put(DBContract.LoginEntry.LOGIN, login.login)
        values.put(DBContract.LoginEntry.NOME, login.nome)
        values.put(DBContract.LoginEntry.SENHA, login.senha)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.LoginEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteLogin(botaiIRid: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.LoginEntry.COLUMN_USER_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(botaiIRid)
        // Issue SQL statement.
        db.delete(DBContract.LoginEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun readLogin(loginID: Int): Login? {
        var login: Login? = null
        val db = writableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery("select * from " + DBContract.LoginEntry.TABLE_NAME + " WHERE " + DBContract.LoginEntry.COLUMN_USER_ID + "='" + loginID + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return Login()
        }

        var id: Int
        var lLogin: String
        var senha: String
        var nome: String
        var email: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(cursor.getColumnIndex(DBContract.LoginEntry.COLUMN_USER_ID))
                lLogin = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.LOGIN))
                senha = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.SENHA))
                nome = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.NOME))
                email = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.EMAIL))

                login = Login(id, lLogin, senha, nome, email)
                cursor.moveToNext()
            }
        }
        return login ?: Login()
    }

    fun readAllLogin(): ArrayList<Login> {
        val login = ArrayList<Login>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.LoginEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var id: Int
        var lLogin: String
        var senha: String
        var nome: String
        var email: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(cursor.getColumnIndex(DBContract.LoginEntry.COLUMN_USER_ID))
                lLogin = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.LOGIN))
                senha = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.SENHA))
                nome = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.NOME))
                email = cursor.getString(cursor.getColumnIndex(DBContract.LoginEntry.EMAIL))

                login.add(Login(id, lLogin, senha, nome, email))
                cursor.moveToNext()
            }
        }
        return login
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Login.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.LoginEntry.TABLE_NAME + " (" +
                    DBContract.LoginEntry.COLUMN_USER_ID + " INTEGER PRIMARY KEY," +
                    DBContract.LoginEntry.SENHA + " TEXT," +
                    DBContract.LoginEntry.NOME + " TEXT," +
                    DBContract.LoginEntry.EMAIL + " TEXT," +
                    DBContract.LoginEntry.LOGIN + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.LoginEntry.TABLE_NAME
    }

}