package com.example.applogin.View.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.applogin.R
import com.example.applogin.View.Registrar.RegistrarLoginActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener {
            login()
        }

        btRegister.setOnClickListener{
            val intent = Intent(this, RegistrarLoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun login() {
        if (editLogin.text.toString() == "admin" && editPasswd.text.toString() == "admin") {
            Toast.makeText(
                applicationContext,
                "logou",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                applicationContext,
                "NÃO logou",Toast.LENGTH_SHORT).show()
        }
    }
}
