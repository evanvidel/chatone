package com.axweb.chatone.user.login.presenter

import com.axweb.chatone.mvp.BaseView

interface LoginContract {

    interface View: BaseView {
        fun goToProfile()
        fun showError(msg: String)
        fun showToast(msg: String)
    }

    interface Presenter {
        fun loginUser(email: String, pass: String)
    }


}