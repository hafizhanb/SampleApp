package com.example.sampleapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.lab_maya.LabMayaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLabMaya.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LabMayaActivity::class.java
                )
            )
        }
    }
}