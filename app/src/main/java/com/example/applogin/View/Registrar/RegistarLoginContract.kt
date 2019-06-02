package com.example.applogin.View.Registrar

import android.content.Context
import com.example.applogin.Model.Login

interface RegistarLoginContract {

    interface View {
        enum class CAMPO{
            NOME, EMAIL, LOGIN, SENHA
        }
        fun showMessage(message: String)
        fun focusErro(campo: CAMPO, msgErro: String)
        fun finalizar()
    }

    interface Presenter {
        fun init(view: View, context: Context)
        fun salvaLogin(login: Login)
    }
}