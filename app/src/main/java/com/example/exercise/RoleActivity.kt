package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise.adapter.RoleAdapter
import com.example.exercise.databinding.ActivityRoleBinding
import com.example.exercise.model.Role
import com.example.exercise.model.Staff
import com.example.exercise.pref.PrefManager

class RoleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoleBinding
    private lateinit var adapter: RoleAdapter
    private lateinit var prefManager: PrefManager
    private var listRole: MutableList<Role> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PrefManager(this)

        adapter = RoleAdapter(RoleAdapter.OnClickEditListener { role ->
//            if (binding.role.text == "admin") {
//                //createUpdateDialog(note)
//            }
        }, RoleAdapter.OnClickListener { role ->
            //showDetailStaffDialog(note)
        }, RoleAdapter.OnSwiper {

        })
        showRoleList()
        binding.backHome.setOnClickListener {
            onBackPressed()
        }

        binding.imgAddRole.setOnClickListener {
            startActivity(Intent(this, AddRoleActivity::class.java))
        }

    }

    private fun showRoleList() {
        listRole = RealmManager.instance!!.loadRole()
        refreshAdapter(listRole)
    }

    private fun refreshAdapter(listRole: MutableList<Role>) {
        adapter.submitList(listRole)
        binding.rcvRole.adapter = adapter
    }
}