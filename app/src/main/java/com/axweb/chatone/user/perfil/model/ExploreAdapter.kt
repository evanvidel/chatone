package com.axweb.chatone.user.perfil.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axweb.chatone.R

class ExploreAdapter : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

     class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_explore,parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }
}