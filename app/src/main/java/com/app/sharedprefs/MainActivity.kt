package com.app.sharedprefs

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.sharedprefs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadData()

        binding?.btnSave?.setOnClickListener {
            saveData()
        }
    }

    private fun saveData()
    {
        val name =  binding?.etName?.text.toString()
        val rollNo =  binding?.etRollNo?.text.toString()

        binding?.tvName?.text = name
        binding?.tvRollNo?.text = rollNo

        val sharedPrefs = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.apply{
            putString("name",name)
            putString("rollNo",rollNo)
        }.apply()

        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
    }

    private fun loadData()
    {
        val sharedPrefs = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
        val savedName = sharedPrefs.getString("name",null)
        val savedRollNo = sharedPrefs.getString("rollNo",null)

        binding?.tvName?.text = savedName
        binding?.tvRollNo?.text = savedRollNo
    }
}