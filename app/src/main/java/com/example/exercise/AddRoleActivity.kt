package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise.databinding.ActivityAddRoleBinding
import com.example.exercise.model.Role
import com.example.exercise.model.RoleAction
import java.util.*

class AddRoleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRoleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            backHome.setOnClickListener {
                onBackPressed()
            }

            btAdd.setOnClickListener {
                val name = edtRoleName.text.toString()
                val des = edtDes.text.toString()
                val isCreateStaff = ckbCrNewStaff.isChecked
                val isEditStaff = ckbEditStaff.isChecked
                val isDelStaff = ckbDelStaff.isChecked
                val isCreateRole = ckbCrRole.isChecked
                val isEditRole = ckbEditRole.isChecked
                val isDelRole = ckbDelRole.isChecked

                if (name.isEmpty()
                ) {
                    return@setOnClickListener
                }
                RealmManager.instance!!.saveRole(
                    Role(
                        UUID.randomUUID().toString(),
                        name,
                        des,
                        "",
                        "1",
                        RoleAction(
                            UUID.randomUUID().toString(),
                            createStaff = isCreateStaff,
                            edittStaff = isEditStaff,
                            delStaff = isDelStaff,
                            creatRole = isCreateRole,
                            editRole = isEditRole,
                            delRole = isDelRole,
                            creatAt = "",
                            creatBy = "1"
                        )
                    )

                )
                startActivity(Intent(this@AddRoleActivity, RoleActivity::class.java))
            }
        }
    }
}