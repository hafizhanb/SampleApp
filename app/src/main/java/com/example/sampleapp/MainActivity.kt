package com.example.sampleapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.cam.CamActivity
import com.example.sampleapp.custom_webview.CustomWebviewActivity
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.lab_maya.LabMayaActivity
import com.example.sampleapp.vr.VrActivity

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

        binding.btnVr.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    VrActivity::class.java
                )
            )
        }

        binding.btnCam.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CamActivity::class.java
                )
            )
        }

        binding.btnCustomWebview.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CustomWebviewActivity::class.java
                )
            )
        }
    }
}