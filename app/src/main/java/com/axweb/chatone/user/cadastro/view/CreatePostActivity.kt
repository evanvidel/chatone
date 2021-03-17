package com.axweb.chatone.user.cadastro.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.R
import com.axweb.chatone.user.perfil.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_post.*
import java.util.*

class CreatePostActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        publish_btn.setOnClickListener {
            val postString = edit_post.text.toString()
            val date = Date()
            val userName = auth.currentUser!!.displayName

            val post = Post(postString,date,userName)

            db.collection("posts").add(post)
                    .addOnSuccessListener {
                        finish()
                    }
                    .addOnFailureListener {
                       // Utils.showError(this,it.message.toString())
                    }

        }
    }
}