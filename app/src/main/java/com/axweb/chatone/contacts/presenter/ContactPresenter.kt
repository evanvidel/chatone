package com.axweb.chatone.contacts.presenter

import android.os.Handler

class ContactPresenter(var view: ContactContract.View?) : ContactContract.Presenter {
    override fun getContacts() {

        view?.showAnimation()
        Handler().postDelayed({
            view?.hideAnimation()

        }, 4000)
    }
}