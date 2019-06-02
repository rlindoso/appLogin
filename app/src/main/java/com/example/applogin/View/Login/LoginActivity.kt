package com.example.applogin.View.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.applogin.Model.Login
import com.example.applogin.R
import com.example.applogin.View.Registrar.RegistrarLoginActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var mPresenter: LoginContract.Presenter
    override fun showMessage(message: String) {
        Toast.makeText(
            applicationContext,
            message,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter()
        mPresenter.init(this, this)
        btLogin.setOnClickListener {
            mPresenter.doLogin(Login(-1, edtLogin.text.toString(), edtPasswd.text.toString(), "", ""))
        }

        btRegister.setOnClickListener{
            val intent = Intent(this, RegistrarLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
