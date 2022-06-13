package com.example.exercise

import android.annotation.SuppressLint
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
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.exercise.adapter.StaffAdapter
import com.example.exercise.databinding.ActivityMainBinding
import com.example.exercise.model.Staff
import com.example.exercise.pref.PrefManager
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var staffAdapter: StaffAdapter
    private lateinit var staffAdapterB: StaffAdapter
    private lateinit var staffAdapterTextChange: StaffAdapter
    private lateinit var staffAdapterBTextChange: StaffAdapter
    private lateinit var prefManager: PrefManager
    private var listStaff: MutableList<Staff> = mutableListOf()
    private var listStaffDefault: MutableList<Staff> = mutableListOf()

    var id = ""

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        prefManager = PrefManager(this)

        staffAdapter = StaffAdapter("deptA", StaffAdapter.OnClickEditListener { note ->
            if (binding.role.text == "admin") {
                createUpdateDialog(note)
            }
        }, StaffAdapter.OnClickListener { note ->
            showDetailStaffDialog(note)
        }, StaffAdapter.OnSwiper {
            if (binding.role.text == "admin") {
                deleteStaf(it.id)
            }
        })

        staffAdapterTextChange = StaffAdapter("deptAChange", StaffAdapter.OnClickEditListener { note ->
            if (binding.role.text == "admin") {
                createUpdateDialog(note)
            }
        }, StaffAdapter.OnClickListener { note ->
            showDetailStaffDialog(note)
        }, StaffAdapter.OnSwiper {
            if (binding.role.text == "admin") {
                deleteStaf(it.id)
            }
        })

        staffAdapterBTextChange = StaffAdapter("deptBChange", StaffAdapter.OnClickEditListener { note ->
            createUpdateDialog(note)
        }, StaffAdapter.OnClickListener { note ->
            showDetailStaffDialog(note)
        }, StaffAdapter.OnSwiper {
            deleteStaf(it.id)
        })

        staffAdapterB = StaffAdapter("deptB", StaffAdapter.OnClickEditListener { note ->
            createUpdateDialog(note)
        }, StaffAdapter.OnClickListener { note ->
            showDetailStaffDialog(note)
        }, StaffAdapter.OnSwiper {
            deleteStaf(it.id)
        })

        val userId = intent.getStringExtra("userName")
        //val userId = prefManager.getId()
        userId?.let {
            viewModel.getInfo(it)?.let { staff ->
                binding.apply {
                    name.text = staff.name
                    address.text = staff.address
                    role.text = staff.roleName
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

        if (binding.role.text == "admin") {
            binding.btCreate.setOnClickListener {
                startActivity(Intent(this, AddStaffActivity::class.java))
            }
        }

        binding.btLogout.setOnClickListener {
            prefManager.removeData()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.search.doOnTextChanged { text, _, _, _ ->
            //val queryText = text.toString().toLowerCase(Locale.getDefault())
            //searchList(queryText)
        }
        binding.search.doAfterTextChanged { text ->
            val key = text.toString()
            var listStaffA: MutableList<Staff> = mutableListOf()
            if (key.length > 1) {
                //listStaff = viewModel.searchStaff(key) as MutableList<Staff>
                binding.rcvDeptA.visibility = View.INVISIBLE
                binding.rcvDeptB.visibility = View.INVISIBLE
                binding.rcvDeptATextChange.visibility = View.VISIBLE
                binding.rcvDeptBChange.visibility = View.VISIBLE

                staffAdapterTextChange.submitList(viewModel.searchStaff(key)?.filter { it.department == "it" })
                binding.rcvDeptATextChange.adapter = staffAdapterTextChange

                staffAdapterBTextChange.submitList(viewModel.searchStaff(key)?.filter { it.department == "kt" })
                binding.rcvDeptATextChange.adapter = staffAdapterTextChange

                //staffAdapter.submitList(listStaff.filter { it.department == "it" })
                //staffAdapter.submitList(viewModel.searchStaff(key))
//                listStaff = mutableListOf()
//                listStaffDefault.forEach { staff ->
//                    if (staff.name?.contains(key, true) == true) {
//                        listStaff.add(staff)
//                        Log.d("123123","addd")
//                    }
//                }
//                staffAdapter.submitList(listStaff.filter { it.department == "it" })
//                staffAdapter.notifyDataSetChanged()
                //staffAdapter.submitList(listStaff.filter { it.department == "it" })

            } else {
                if (key.isEmpty()) {
                    binding.rcvDeptA.visibility = View.VISIBLE
                    binding.rcvDeptB.visibility = View.VISIBLE
                    binding.rcvDeptATextChange.visibility = View.INVISIBLE
                    binding.rcvDeptBChange.visibility = View.INVISIBLE
                }
                val x = 1
                Log.d("123123", "12321")
                //staffAdapter.notifyDataSetChanged()
            }


            //staffAdapter.submitList(listStaff)
//            staffAdapter.notifyDataSetChanged()
//            staffAdapterB.notifyDataSetChanged()
            val x = 3
            Log.d("123123", "jhajsd")

//            staffAdapter.submitList(listStaff.filter { it.department == "it" })
//            staffAdapterB.submitList(listStaff.filter { it.department == "kt" })
//            binding.rcvDeptA.adapter = staffAdapter
//            binding.rcvDeptB.adapter = staffAdapterB

        }

//        listStaff = viewModel.searchStaff("") as MutableList<Staff>
//        staffAdapter.submitList(listStaff.filter { it.department == "it" })
//        staffAdapterB.submitList(listStaff.filter { it.department == "kt" })
//        binding.rcvDeptA.adapter = staffAdapter
//        binding.rcvDeptB.adapter = staffAdapterB

        viewModel.allNotes.observe(this) { allNotes ->
            listStaff = allNotes
            staffAdapter.submitList(listStaff.filter { it.department == "it" })
            staffAdapterB.submitList(listStaff.filter { it.department == "kt" })
            binding.rcvDeptA.adapter = staffAdapter
            binding.rcvDeptB.adapter = staffAdapterB
        }
    }

    private fun searchList(queryText: String) {
        listStaff = viewModel.searchStaffByName(queryText)
        staffAdapter.submitList(listStaff.filter { it.department == "it" })
        staffAdapterB.submitList(listStaff.filter { it.department == "kt" })
        binding.rcvDeptA.adapter = staffAdapter
        binding.rcvDeptB.adapter = staffAdapterB
    }

    private fun deleteStaf(id: String) {
        //viewModel.deleteNote(id)
    }

    private fun createUpdateDialog(staff: Staff) {
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
        tvEmail.setText(staff.email)
        tvDept.setText(staff.department)
        tvAddress.setText(staff.address)
        pass.setText(staff.password)
        btOK.visibility = View.GONE

        AlertDialog.Builder(this).apply {
            setView(dialogView)
            setPositiveButton("Ok") { _, _ ->
                viewModel.updateNote(
                    staff.id,
                    tvName.text.toString(),
                    pass.text.toString(),
                    tvEmail.text.toString(),
                    tvDept.text.toString(),
                    tvAddress.text.toString()
                )
                staffAdapter.notifyDataSetChanged()
                staffAdapterB.notifyDataSetChanged()
            }
            setNegativeButton("Cancel") { _, _ ->

            }.show()
        }
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