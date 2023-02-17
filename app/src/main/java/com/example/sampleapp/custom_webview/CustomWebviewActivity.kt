package com.example.sampleapp.custom_webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.databinding.ActivityCustomWebviewBinding


class CustomWebviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomWebviewBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }
//        binding.wv.text = Raw.dummyData + "<p></p>" + Raw.dummyImage
//        binding.wv.text = Raw.dummyMath
        val data =
            Raw.dummyData + "<p></p>" + Raw.dummyImage + "<p></p>" + Raw.dummyMath + "<p></p>" + Raw.url
        val newData = when {
            data.contains("""\(""") -> {
                data.replace("""\(""", "$", true)
                    .replace("""\)""", "$", true)
            }
            data.contains("""\[""") -> {
                data.replace("""\[""", "$$", true)
                    .replace("""\]""", "$$", true)
            }
            else -> {
                data
            }
        }
        binding.wv.text = newData
        binding.wv.webViewClient = object : WebViewClient() {
            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                binding.fabClose.visibility =
                    if (binding.wv.canGoBack()) View.VISIBLE else View.GONE
                super.doUpdateVisitedHistory(view, url, isReload)
            }
        }
        binding.fabClose.setOnClickListener {
            if (binding.wv.canGoBack()) {
                binding.wv.goBack()
            }
        }
//        binding.wv.setOnTouchListener { v, event ->
//            v.parent.requestDisallowInterceptTouchEvent(true)
//            v.onTouchEvent(event)
//            true
//        }
    }

//    @SuppressLint("SetJavaScriptEnabled")
//    private fun initWebView(url: String) {
//        binding.webview.settings.useWideViewPort = true
//        binding.webview.settings.loadWithOverviewMode = true
//        binding.webview.settings.javaScriptEnabled = true
//        binding.webview.settings.allowFileAccess = true
//        binding.webview.settings.allowContentAccess = true
//        binding.webview.settings.domStorageEnabled = true
//
//        binding.webview.webChromeClient = object : WebChromeClient() {
//            override fun onProgressChanged(view: WebView, newProgress: Int) {
//                super.onProgressChanged(view, newProgress)
//                binding.progressBar.progress = newProgress
//                binding.progressBar.visibility = if (newProgress == 100) View.GONE else View.VISIBLE
//            }
//        }
//        binding.webview.loadUrl(url)
//    }
}