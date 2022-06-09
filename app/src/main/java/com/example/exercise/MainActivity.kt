package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btLogin: Button
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btLogin = findViewById(R.id.btLogout)
        prefManager = PrefManager(this)
        btLogin.setOnClickListener {
            prefManager.removeData()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // adapter.notifyDataSetChanged()
        }
    }
}