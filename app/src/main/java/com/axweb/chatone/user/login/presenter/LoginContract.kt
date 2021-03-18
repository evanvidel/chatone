package com.axweb.chatone.user.login.presenter

import com.axweb.chatone.mvp.BaseMVPView

interface LoginContract {

    interface View: BaseMVPView {
        fun redirect()
        fun showError(msg: String)
        fun showToast(msg: String)
    }

    interface Presenter {
        fun loginUser(email: String, pass: String)
    }


}