package com.axweb.chatone.user.perfil.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarConfig.setOnClickListener {
            onBackPressed()
        }
    }
}