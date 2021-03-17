package com.axweb.chatone.user.login.presenter

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginPresenter(var view: LoginContract.View?) : LoginContract.Presenter {


    override fun loginUser(email: String, pass: String) {
        view?.showLoader()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                view?.showToast("Login efetuado com sucesso")
                view?.hideLoader()
                view?.goToProfile()

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