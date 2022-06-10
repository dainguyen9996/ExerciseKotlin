package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.exercise.databinding.ActivityLoginBinding
import com.example.exercise.pref.PrefManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        prefManager = PrefManager(this)
        checkLogin()

        binding.btLogin.setOnClickListener {
            val userName = binding.edtUserName.text.toString() ?: null
            val pass = binding.edtPass.text.toString()
            if (!userName.isNullOrEmpty() && !pass.isNullOrEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                if (userName == "admin@gm.vn" && pass == "admin") {
                    prefManager.setUsername(userName)
                    intent.putExtra("userName", viewModel.addAdmin(userName, pass))
                    startActivity(intent)
                } else {
                    if (viewModel.login(userName, pass)) {
                        prefManager.setUsername(userName)
                        intent.putExtra("userName", viewModel.getUserId(userName))
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun checkLogin() {
        if (prefManager.getUsername() != "") {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", prefManager.getUsername()?.let { viewModel.getUserId(it) })
            startActivity(intent)
        }
    }
}