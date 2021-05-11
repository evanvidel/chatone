package com.axweb.chatone.contacts.presenter

interface ContactContract {

    interface View {
        fun showAnimation()
        fun hideAnimation()
    }
    interface  Presenter {
        fun getContacts()
    }
}