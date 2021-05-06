package com.axweb.chatone.contacts.presenter

import com.axweb.chatone.mvp.BaseMVPView

interface ContactContract {

    interface View {
        fun showAnimation()
        fun hideAnimation()
    }
    interface  Presenter {
        fun getContacts()
    }
}