package com.example.applogin.View.Registrar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.applogin.Model.Login
import com.example.applogin.R
import kotlinx.android.synthetic.main.activity_registrar_login.*

class RegistrarLoginActivity : AppCompatActivity(), RegistarLoginContract.View {
    override fun finalizar() {
        onBackPressed()
    }

    override fun focusErro(campo: RegistarLoginContract.View.CAMPO, msgErro: String) {
        when(campo) {
            RegistarLoginContract.View.CAMPO.LOGIN -> {
                edtLogin.isFocusable = true
                edtLogin.error = msgErro
                edtLogin.requestFocus()
            }

            RegistarLoginContract.View.CAMPO.SENHA -> {
                edtSenha.isFocusable = true
                edtSenha.error = msgErro
                edtSenha.requestFocus()
            }

            RegistarLoginContract.View.CAMPO.NOME -> {
                edtNome.isFocusable = true
                edtNome.error = msgErro
                edtNome.requestFocus()
            }

            RegistarLoginContract.View.CAMPO.EMAIL -> {
                edtEmail.isFocusable = true
                edtEmail.error = msgErro
                edtEmail.requestFocus()
            }
        }
    }

    lateinit var mPresenter: RegistarLoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_login)

        mPresenter = RegistrarLoginPresenter()
        mPresenter.init(this, this)

        btCancelar.setOnClickListener {
            onBackPressed()
        }

        btSalvar.setOnClickListener{
            mPresenter.salvaLogin(Login(-1,
                edtLogin.text.toString(),
                edtSenha.text.toString(),
                edtNome.text.toString(),
                edtEmail.text.toString()))
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}
