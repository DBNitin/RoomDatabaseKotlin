package com.example.roomdatabsekotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabsekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DBHandler.getDB(this)
        val list = db.studentDao.getAllStudent()
        binding.rvstudent.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(list as ArrayList<studentVM>, this)
        adapter.notifyDataSetChanged()
        binding.rvstudent.adapter = adapter

        binding.btnsave.setOnClickListener {
            val name = binding.studentname.text.toString()
            val email = binding.studentemail.text.toString()
            if (name.isEmpty() || email.isEmpty()) {
                binding.txtmsg.isVisible = true;
                binding.txtmsg.text = "Please fill the student details"
            }
            else {
                binding.txtmsg.isVisible = false;
                val student = StudentTable(name, email)
                val id = db.studentDao.insertStudent(student)
            }



            binding.rvstudent.layoutManager = LinearLayoutManager(this)
            adapter = StudentAdapter(list as ArrayList<studentVM>, this)
            adapter.notifyDataSetChanged()
            binding.rvstudent.adapter = adapter
        }
    }
}