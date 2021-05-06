package com.axweb.chatone.user.perfil.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.axweb.chatone.R
import com.axweb.chatone.databinding.ActivityTermosBinding
import com.axweb.chatone.main.view.MainActivity
import java.io.IOException
import java.io.InputStream
import java.nio.channels.AsynchronousFileChannel.open

class TermosActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityTermosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarTermos.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val webview:WebView = binding.webviewTermos
        webview.webChromeClient = WebChromeClient()

        try {
            val fin: InputStream  = assets.open("termos.html")
            val buffer = ByteArray(fin.available())
            fin.read(buffer)
            fin.close()

            webview.loadData(String(buffer),"text/html","UTF-8")
        } catch (e: IOException){
            e.printStackTrace()
        }


    }
}