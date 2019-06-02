package com.example.applogin.View.Registrar

import android.content.Context
import com.example.applogin.DBHelper.DBHelper
import com.example.applogin.Model.Login

class RegistrarLoginPresenter: RegistarLoginContract.Presenter {
    var mView: RegistarLoginContract.View? = null
    lateinit var dbHelper : DBHelper
    override fun init(view: RegistarLoginContract.View, context: Context) {
        mView = view
        dbHelper = DBHelper(context)
    }

    override fun salvaLogin(login: Login) {
        if (validaUsuario(login)) {
            if (dbHelper.insertLogin(login)) {
                mView!!.showMessage("Usuário cadastrado com sucesso!")
                mView!!.finalizar()
            }
        }
    }

    fun validaUsuario(login: Login): Boolean{
        var isSuccess = true
        if (login.senha!!.trim().isEmpty()){
            mView!!.focusErro(RegistarLoginContract.View.CAMPO.SENHA, "Senha precisa ser informado.")
            isSuccess = false
        }
        if (login.login!!.trim().isEmpty()){
            mView!!.focusErro(RegistarLoginContract.View.CAMPO.LOGIN, "Login precisa ser informado.")
            isSuccess = false
        } else if (dbHelper.readLogin(login.login)!!.id!! > 0){
            mView!!.focusErro(RegistarLoginContract.View.CAMPO.LOGIN, "Login " + login.login + " já em uso.")
            isSuccess = false
        }
        if (login.email!!.trim().isEmpty()){
            mView!!.focusErro(RegistarLoginContract.View.CAMPO.EMAIL, "E-mail precisa ser informado.")
            isSuccess = false
        }
        if (login.nome!!.trim().isEmpty()){
            mView!!.focusErro(RegistarLoginContract.View.CAMPO.NOME, "Nome precisa ser informado.")
            isSuccess = false
        }
        return isSuccess
    }
}