package com.example.sampleapp.vr

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.databinding.ActivityVrDetailBinding


class VrDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVrDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVrDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }
        intent?.getStringExtra("data")?.let {
            initWebView(it)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url: String) {
        binding.webview.settings.useWideViewPort = true
        binding.webview.settings.loadWithOverviewMode = true
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.allowFileAccess = true
        binding.webview.settings.allowContentAccess = true
        binding.webview.settings.domStorageEnabled = true

        binding.webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.progressBar.progress = newProgress
                binding.progressBar.visibility = if (newProgress == 100) View.GONE else View.VISIBLE
            }
        }
        binding.webview.loadUrl(url)
    }
}