package com.example.applogin.View.Login

import android.content.Context
import com.example.applogin.Model.Login

interface LoginContract {
    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        fun init(view: View, context: Context)
        fun doLogin(login: Login)
    }
}