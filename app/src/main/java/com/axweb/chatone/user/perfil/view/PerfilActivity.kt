package com.axweb.chatone.user.perfil.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.core.getUser
import com.axweb.chatone.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarPerfil.setOnClickListener {
            onBackPressed()
        }

        getUser(FirebaseAuth.getInstance().currentUser?.uid) {
            binding.nomePerfil.text = it?.username
            Picasso.get().load(it?.photo_path).into(binding.imgPerfil)
        }

    }
}

