package com.example.applogin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLogin.setOnClickListener {
            login()
        }
    }

    fun login() {
        if (editLogin.getText().toString().equals("admin") && editPasswd.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(),
                "logou",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(getApplicationContext(),
                "NÃO logou",Toast.LENGTH_SHORT).show()
        }
    }
}
