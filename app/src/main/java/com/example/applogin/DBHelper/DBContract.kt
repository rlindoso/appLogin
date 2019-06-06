package com.example.applogin.DBHelper

import android.provider.BaseColumns

object DBContract {
    /* Inner class that defines the table contents */
    class LoginEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "User"
            val COLUMN_USER_ID = "id"
            val LOGIN = "login"
            val NOME = "nome"
            val EMAIL = "email"
            val ENCRYPTED = "encrypted"
            val SALT = "salt"
            val IV = "iv"
        }
    }

}