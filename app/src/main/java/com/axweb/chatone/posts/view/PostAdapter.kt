package com.axweb.chatone.posts.view

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.axweb.chatone.R
import com.axweb.chatone.posts.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PostAdapter (private val activity: Activity,private val dataset: List<Post>): RecyclerView.Adapter<PostAdapter.ViewHolder>(){
    private val auth = FirebaseAuth.getInstance()
    private  val db  = FirebaseFirestore.getInstance()

    class ViewHolder (val layout: View): RecyclerView.ViewHolder(layout){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_post,parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = dataset[position]
        val likes = post.likes!!.toMutableList()
        var liked = likes.contains(auth.uid)

       /* holder.layout.likesCount_tv.text = "${likes.size}likes"
        holder.layout.namePerson_tv.text = post.username+
        holder.layout.post_tv.text = post.post

        val sdf =SimpleDateFormat("dd/M/yyyy hh:mm a")

        holder.layout.fecha_tv.text = sdf.format(post.date)
        setColor()*/


    }

    override fun getItemCount(): Int {
        return dataset.size
    }
    private fun setColor(liked: Boolean,likeButton: Button) {
        if(liked) likeButton.setTextColor(ContextCompat.getColor(activity,R.color.colorAccent))
        else {
            likeButton.setTextColor(Color.BLACK)
        }
    }
}