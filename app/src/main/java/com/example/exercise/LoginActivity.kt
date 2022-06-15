package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.exercise.databinding.ActivityLoginBinding
import com.example.exercise.model.Role
import com.example.exercise.model.RoleAction
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
                        if (realm.loadStaffs().firstOrNull { it.email == userName } == null) {
                            realm.saveAdmin(
                                Staff(
                                    "1",
                                    "admin",
                                    userName,
                                    pass,
                                    "",
                                    "IT Security",
                                    "hcm",
                                    "",
                                    Role(
                                        "1",
                                        "admin",
                                        "All rule are available",
                                        "",
                                        "1",
                                        RoleAction(
                                            "1",
                                            createStaff = true,
                                            edittStaff = true,
                                            delStaff = true,
                                            creatRole = true,
                                            editRole = true,
                                            delRole = true,
                                            creatAt = "",
                                            creatBy = "1"
                                        )
                                    )
                                )
                            )
//                            realm.saveRole(
//                                Role(
//                                    "1",
//                                    "admin",
//                                    "",
//                                    "",
//                                    "admin",
//                                    RoleAction(
//                                        "1",
//                                        createStaff = true,
//                                        edittStaff = true,
//                                        delStaff = true,
//                                        creatRole = true,
//                                        editRole = true,
//                                        delRole = true,
//                                        creatAt = "",
//                                        creatBy = "1"
//                                    )
//                                )
//                            )
                        }
                    }
                    intent.putExtra("userName", "")
                    startActivity(intent)
                } else {
                    RealmManager.instance?.let { realm ->
                        if (realm.loadStaffs().firstOrNull { it.email == userName } == null) {
                            prefManager.setUsername(userName)
                            intent.putExtra("userName", "")
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }


    private fun checkLogin() {
        if (prefManager.getUsername() != "") {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", "")
            startActivity(intent)
            RealmManager.instance?.let { realm ->
                val size = realm.loadRole().size
                Log.d("12312323", size.toString())
            }
        }
    }
}