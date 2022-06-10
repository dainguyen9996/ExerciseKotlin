package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.exercise.adapter.StaffAdapter
import com.example.exercise.databinding.ActivityMainBinding
import com.example.exercise.model.Staff
import com.example.exercise.pref.PrefManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var staffAdapter: StaffAdapter
    private lateinit var staffAdapterB: StaffAdapter
    private lateinit var prefManager: PrefManager

    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        prefManager = PrefManager(this)

        staffAdapter = StaffAdapter("deptA", StaffAdapter.OnClickEditListener { note ->
            createUpdateDialog(note)
        }, StaffAdapter.OnClickListener { note ->
            showDetailStaffDialog(note)
        }, StaffAdapter.OnSwiper {
            id = it.id
        })

        staffAdapterB = StaffAdapter("deptB", StaffAdapter.OnClickEditListener { note ->
            createUpdateDialog(note)
        }, StaffAdapter.OnClickListener { note ->
            showDetailStaffDialog(note)
        }, StaffAdapter.OnSwiper {
            id = it.id
        })

        val userId = intent.getStringExtra("userName")
        userId?.let {
            viewModel.getInfo(it)?.let { staff ->
                binding.apply {
                    name.text = staff.name
                    address.text = staff.address
                    role.text = "IT"
                    depart.text = staff.department
                    email.text = staff.email
                }
            }
        }

        binding.imgMenu.setOnClickListener {
            binding.subLayout.visibility = View.VISIBLE
        }

        binding.closeMenu.setOnClickListener {
            binding.subLayout.visibility = View.INVISIBLE
        }

        binding.btCreate.setOnClickListener {
            startActivity(Intent(this, AddStaffActivity::class.java))
        }

        binding.btLogout.setOnClickListener {
            prefManager.removeData()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        viewModel.allNotes.observe(this) { allNotes ->
            staffAdapter.submitList(allNotes)
            binding.rcvDeptA.adapter = staffAdapter
        }
    }

    private fun createUpdateDialog(staff: Staff) {

    }

    private fun showDetailStaffDialog(staff: Staff) {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.activity_add_staff, viewGroup, false)

        val tvName = dialogView.findViewById<EditText>(R.id.tvName)
        val tvEmail = dialogView.findViewById<EditText>(R.id.tvEmail)
        val pass = dialogView.findViewById<EditText>(R.id.tvPass)
        val tvDept = dialogView.findViewById<EditText>(R.id.tvDept)
        val tvAddress = dialogView.findViewById<EditText>(R.id.tvAddress)
        val btOK = dialogView.findViewById<Button>(R.id.btAdd)

        tvName.setText(staff.name)
        tvName.isEnabled = false
        tvEmail.setText(staff.email)
        tvEmail.isEnabled = false
        tvDept.setText(staff.department)
        tvDept.isEnabled = false
        tvAddress.setText(staff.address)
        tvAddress.isEnabled = false
        pass.visibility = View.GONE
        btOK.visibility = View.GONE

        AlertDialog.Builder(this).apply {
            setView(dialogView)
            setPositiveButton("Ok") { _, _ ->
            }.show()
        }
    }
}