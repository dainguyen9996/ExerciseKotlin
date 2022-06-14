package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.exercise.databinding.ActivityLoginBinding
import com.example.exercise.model.Staff
import com.example.exercise.pref.PrefManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PrefManager(this)
        checkLogin()

        binding.btLogin.setOnClickListener {
            val userName = binding.edtUserName.text.toString() ?: null
            val pass = binding.edtPass.text.toString()
            if (!userName.isNullOrEmpty() && !pass.isNullOrEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                if (userName == "ad" && pass == "1") {
                    prefManager.setUsername(userName)
                    RealmManager.instance?.let { realm ->
                        if (realm.loadNotes().firstOrNull { it.email == userName } == null) {
                            realm.saveAdmin(
                                Staff(
                                    "1",
                                    "admin",
                                    pass,
                                    userName,
                                    "",
                                    "",
                                    "",
                                    "",
                                    "admin"
                                )
                            )
                        }
                    }
                    intent.putExtra("userName", "")
                    startActivity(intent)
                } else {
                    RealmManager.instance?.let { realm ->
                        if (realm.loadNotes().firstOrNull { it.email == userName } == null) {
                            prefManager.setUsername(userName)
                            intent.putExtra("userName", "")
//                        prefManager.setId(viewModel.getUserId(userName))
                            startActivity(intent)
                        }
                    }
                    prefManager.setUsername(userName)
//                        intent.putExtra("userName", viewModel.getUserId(userName))
//                        prefManager.setId(viewModel.getUserId(userName))
                    startActivity(intent)
                }
            }
        }
    }


    private fun checkLogin() {
        if (prefManager.getUsername() != "") {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", "")
            startActivity(intent)
        }
    }
}