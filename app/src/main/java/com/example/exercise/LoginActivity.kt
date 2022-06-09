package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btLogin: TextView
    private lateinit var username: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        checkLogin()
        btLogin.setOnClickListener {
            clickLogin()
        }
    }

    private fun init() {
        prefManager = PrefManager(this)
        etUsername = findViewById(R.id.email)
        etPassword = findViewById(R.id.pass)
        btLogin = findViewById(R.id.btLogin)
    }

    private fun checkLogin() {
        if (prefManager.getUsername() != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }

    fun clickLogin() {
        username = etUsername.text.toString().trim()
        password = etPassword.text.toString().trim()

        if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
            prefManager.setUsername(username)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }
}