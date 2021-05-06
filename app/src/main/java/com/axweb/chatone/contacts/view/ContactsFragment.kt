package com.axweb.chatone.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.axweb.chatone.contacts.presenter.ContactContract
import com.axweb.chatone.contacts.presenter.ContactPresenter
import com.axweb.chatone.databinding.FragmentContactsBinding
import com.axweb.chatone.mvp.BaseMVPFragment
import com.axweb.chatone.core.setTitleToolbar

class ContactsFragment: BaseMVPFragment<FragmentContactsBinding>(),ContactContract.View{

   private val presenter = ContactPresenter(this)

   override fun onCreateView(inflater: LayoutInflater): View {
      binding = FragmentContactsBinding.inflate(inflater)


      

      setTitleToolbar("Contatos")
      return binding.root

   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)


      binding.rvc.apply {

         setHasFixedSize(true)
         layoutManager = LinearLayoutManager(context)
         adapter = ContactAdapter()
      }
   }

   override fun onDestroy() {
      presenter.view = null
      super.onDestroy()
   }

   override fun showAnimation() {
      binding.animationContacts.visibility = View.VISIBLE
   }

   override fun hideAnimation() {
      binding.animationContacts.visibility = View.GONE
   }


}