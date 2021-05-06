package com.axweb.chatone.posts.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axweb.chatone.R
import com.axweb.chatone.posts.model.Post
import com.axweb.chatone.user.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_post.view.*
import java.text.SimpleDateFormat

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PostAdapter(val list: ArrayList<Post>, val user: User) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = itemView.post_tv
        val nameUser = itemView.namePerson_tv
        val date = itemView.date_post
        val image = itemView.foto_post
        val imageUser = itemView.img_post

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return ViewHolder(layout)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm a")

        val post = list[position]
        holder.text.text = post.text
        holder.nameUser.text = user.getFullName()
        holder.date.text = sdf.format(post.date)

        //[lista].sortedBy { [data] }

        Picasso.get().load(post.image).into(holder.image)
        Picasso.get().load(user.photo_path).into(holder.imageUser)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}