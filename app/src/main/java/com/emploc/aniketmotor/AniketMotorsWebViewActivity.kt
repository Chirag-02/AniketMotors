package com.emploc.aniketmotor

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.emploc.databinding.ActivityAniketMotorsWebViewBinding

class AniketMotorsWebViewActivity : AppCompatActivity() {
    var binding: ActivityAniketMotorsWebViewBinding? = null
    var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAniketMotorsWebViewBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)
        if (intent.extras != null) {
            url = intent?.extras?.getString("url").toString()
        }
        println("URL:  $url")
        initializeWebView(url!!)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView(url: String) {
        binding?.webview?.webChromeClient = WebChromeClient()
        binding?.webview?.webViewClient = WebViewClient()
        binding?.webview?.settings?.javaScriptEnabled = true
        binding?.webview?.settings?.domStorageEnabled = true
        binding?.webview?.settings?.setSupportMultipleWindows(true)
        binding?.webview?.requestFocus(View.FOCUS_DOWN)
        binding?.webview?.clearCache(true)
        binding?.webview?.clearHistory()
        binding?.webview?.loadUrl(url)
        binding?.webview?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && binding?.webview?.canGoBack() == true) {
                    binding?.webview?.goBack()
                    return true
                }
                return false
            }
        })
    }
}