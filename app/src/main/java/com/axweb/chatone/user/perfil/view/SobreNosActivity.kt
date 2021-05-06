package com.axweb.chatone.user.perfil.view

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.databinding.ActivitySobreNosBinding
import java.io.IOException
import java.io.InputStream

class SobreNosActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySobreNosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySobreNosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarSobre.setOnClickListener {
           onBackPressed()
        }

        val webView:WebView = binding.webview

        try {
            val fin: InputStream = assets.open("about.html")
            val buffer = ByteArray(fin.available())
            fin.read(buffer)
            fin.close()

            webView.loadData(String(buffer),"text/html", "UTF-8")

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}