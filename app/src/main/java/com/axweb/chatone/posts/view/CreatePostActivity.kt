package com.axweb.chatone.posts.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.SplashActivity
import com.axweb.chatone.SucessoActivity
import com.axweb.chatone.core.getCurrentUser
import com.axweb.chatone.core.newPost
import com.axweb.chatone.core.showError
import com.axweb.chatone.databinding.ActivityCreatePostBinding
import com.axweb.chatone.main.view.MainActivity
import com.axweb.chatone.posts.model.Post
import com.axweb.chatone.user.model.User
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.card_post.view.*
import java.util.*

class CreatePostActivity : AppCompatActivity() {

 private var selecionarUri: Uri? = null
    var user: User? = null

    private lateinit var binding: ActivityCreatePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animation.visibility = View.GONE

        toolbar_title?.setOnClickListener {
            onBackPressed()
        }

        binding.selectImage.setOnClickListener {
            selecionarFoto()
        }


        getCurrentUser { u ->
            binding.publishBtn.setOnClickListener {


                val textPost = edit_post.text.toString()
                val isEmpty =  textPost.isEmpty() && selecionarUri == null

                if (isEmpty) {
                    showError("Escreva um texto ou selecione uma imagem")

                }else{

                    user = u
                    binding.animation.visibility = View.VISIBLE

                    salvarDados()
                    telasucesso()

                }



            }
        }
    }

    private fun telasucesso() {
        startActivity(Intent(this,SucessoActivity::class.java))
        onBackPressed()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            selecionarUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selecionarUri)
            binding.selectImage.setImageBitmap(bitmap)

        }
    }

    private fun selecionarFoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)

    }
    private  fun salvarDados() {
        val nomeArquivo = UUID.randomUUID().toString()
        val referencia = FirebaseStorage.getInstance().getReference(
                "/imagens/${nomeArquivo}")


        if (selecionarUri != null) {

            selecionarUri?.let {

                referencia.putFile(it)
                        .addOnSuccessListener {
                            referencia.downloadUrl.addOnSuccessListener { url ->
                                val postString = edit_post.text.toString()
                                val date = Calendar.getInstance()
                                val post = Post(text = postString, date = date.time, image = url.toString())


                                user.newPost(post) {

                                    if (it) {


                                        finish()
                                    } else {
                                        showError("Erro! Tente novamente mais tarde!")
                                    }
                                }

                            }
                        }
            }

        }else{
            val postString = edit_post.text.toString()
            val date = Calendar.getInstance()
            val post = Post(text = postString, date = date.time)

            user.newPost(post) {
                if (it) {
                    finish()
                } else {
                    showError("Erro! Tente novamente mais tarde!")
                }
            }
        }

    }

}






