package com.example.applogin.View.Login

import android.content.Context
import com.example.applogin.DBHelper.DBHelper
import com.example.applogin.Model.Login

class LoginPresenter: LoginContract.Presenter {
    var mView: LoginContract.View? = null
    lateinit var dbHelper : DBHelper

    override fun init(view: LoginContract.View, context: Context) {
        mView = view
        dbHelper = DBHelper(context)
    }

    override fun doLogin(login: Login) {
        var userLogged: Login = dbHelper.getLogin(login)
        if (userLogged.id!! > 0){
            mView!!.showMessage("Usuário " + userLogged.nome + " logado com sucesso!!")
        } else{
            mView!!.showMessage("Usuário ou senha inválido!!")
        }
    }

}