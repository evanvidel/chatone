package com.axweb.chatone.user.login.presenter

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.axweb.chatone.user.login.view.FormLoginActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.activity_cadastrar.*

class LoginPresenter(var view: LoginContract.View?) : LoginContract.Presenter {


    override fun loginUser(email: String, pass: String) {
        view?.showLoader()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {

                view?.showToast("Login efetuado com sucesso")
                view?.hideLoader()
                view?.redirect()




            }
        }.addOnFailureListener {
            view?.hideLoader()
            val error = it

            when {

                error is FirebaseAuthInvalidCredentialsException -> view?.showError("E-mail ou senha estão incorretos")
                error is FirebaseNetworkException -> view?.showError("Sem conexão com a internet")
                else -> view?.showError("Erro ao logar")
            }
        }
    }
}