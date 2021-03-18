package com.axweb.chatone.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.axweb.chatone.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = FragmentContactsBinding.inflate(inflater)
        return binder.root
    }


}