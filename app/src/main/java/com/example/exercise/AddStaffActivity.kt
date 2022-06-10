package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.exercise.databinding.ActivityAddStaffBinding

class AddStaffActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStaffBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddStaffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.apply {
            btAdd.setOnClickListener {
                if (tvName.text.toString().isNullOrEmpty() || tvAddress.text.toString()
                        .isNullOrEmpty() || tvEmail.text.toString()
                        .isNullOrEmpty() || tvDept.text.toString()
                        .isNullOrEmpty() || tvPass.text.toString().isNullOrEmpty()
                ) {
                    return@setOnClickListener
                }
                viewModel.addStaff(
                    tvName.text.toString(),
                    tvEmail.text.toString(),
                    tvPass.text.toString(),
                    tvDept.text.toString(),
                    tvAddress.text.toString()
                )
                startActivity(Intent(this@AddStaffActivity, MainActivity::class.java))
            }
        }
    }
}