package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise.databinding.ActivityAddStaffBinding
import com.example.exercise.model.Role
import com.example.exercise.model.RoleAction
import com.example.exercise.model.Staff
import java.util.*

class AddStaffActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStaffBinding

    // private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddStaffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.apply {
            btAdd.setOnClickListener {
                if (tvName.text.toString().isNullOrEmpty() || tvName.text.toString()
                        .isNullOrEmpty() || tvEmail.text.toString()
                        .isNullOrEmpty() || tvDept.text.toString()
                        .isNullOrEmpty() || tvPass.text.toString()
                        .isNullOrEmpty() || tvAddress.text.toString().isNullOrEmpty()
                ) {
                    return@setOnClickListener
                }
                RealmManager.instance!!.saveStaff(
                    Staff(
                        UUID.randomUUID().toString(),
                        tvName.text.toString(),
                        tvName.text.toString(),
                        tvPass.text.toString(),
                        "",
                        tvDept.text.toString(),
                        tvAddress.text.toString(),
                        "123123",
                        Role(
                            UUID.randomUUID().toString(),
                            "staff",
                            "",
                            "",
                            "admin",
                            RoleAction(
                                UUID.randomUUID().toString(),
                                createStaff = false,
                                edittStaff = false,
                                delStaff = false,
                                creatRole = false,
                                editRole = false,
                                delRole = false,
                                creatAt = "",
                                creatBy = "1"
                            )
                        )
                    )
                )
                startActivity(Intent(this@AddStaffActivity, MainActivity::class.java))
            }
        }
    }
}