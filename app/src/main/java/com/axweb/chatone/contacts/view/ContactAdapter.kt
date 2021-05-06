package com.axweb.chatone.contacts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axweb.chatone.R

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contato = LayoutInflater.from(parent.context).inflate(R.layout.item_contacts,parent,false)
        return ViewHolder(contato)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 8
    }


}