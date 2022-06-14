package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise.databinding.ActivityAddStaffBinding
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
                if (tvName.text.toString().isNullOrEmpty() || tvAddress.text.toString()
                        .isNullOrEmpty() || tvEmail.text.toString()
                        .isNullOrEmpty() || tvDept.text.toString()
                        .isNullOrEmpty() || tvPass.text.toString().isNullOrEmpty()
                ) {
                    return@setOnClickListener
                }
                RealmManager.instance!!.saveNote(Staff(UUID.randomUUID().toString(), "123", "12321", "123123", "","kt", "12231", "123123", "staff"))
                startActivity(Intent(this@AddStaffActivity, MainActivity::class.java))
            }
        }
    }
}