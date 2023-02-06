package com.example.sampleapp.vr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.databinding.ActivityVrBinding


class VrActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.btn1.setOnClickListener { goToDetail("https://community-portal-prod-previewdata.s3.us-west-2.amazonaws.com/files/2020/03/2d8b9963a9ab0671afde/index.html") }
        binding.btn2.setOnClickListener { goToDetail("https://community-portal-prod-previewdata.s3.us-west-2.amazonaws.com/files/2020/03/b2a38eab6560c7dbeadd/index.html") }
        binding.btn3.setOnClickListener { goToDetail("https://community-portal-prod-previewdata.s3.us-west-2.amazonaws.com/files/2020/03/282623e0fb10027387a5/index.html") }
    }

    private fun goToDetail(url: String) {
        startActivity(Intent(this, VrDetailActivity::class.java).apply { putExtra("data", url) })
    }

}