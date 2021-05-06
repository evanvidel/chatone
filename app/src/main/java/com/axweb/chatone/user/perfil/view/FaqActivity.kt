package com.axweb.chatone.user.perfil.view

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.databinding.ActivityFaqBinding
import com.axweb.chatone.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_faq.*
import java.io.IOException
import java.io.InputStream

class FaqActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFaqBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarFaq.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


        val webView: WebView = binding.webview
        webView.webChromeClient = WebChromeClient()

        try {
            // read the HTML from the file
            val fin: InputStream = assets.open("faq.html")
            val buffer = ByteArray(fin.available())
            fin.read(buffer)
            fin.close()

            // load the HTML
            webview.loadData(String(buffer), "text/html", "UTF-8")
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}