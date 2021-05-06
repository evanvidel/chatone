package com.axweb.chatone.user.perfil.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.axweb.chatone.R
import com.axweb.chatone.databinding.ActivityExploreBinding
import com.axweb.chatone.user.perfil.model.ExploreAdapter

class ExploreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarExplore.setOnClickListener {
            onBackPressed()
        }

        binding.rve.apply {
            setHasFixedSize(true)

            layoutManager = GridLayoutManager(context,2)
            adapter = ExploreAdapter()

        }
    }
}