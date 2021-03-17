package com.axweb.chatone.user.cadastro.controller

import android.widget.Toast
import com.axweb.chatone.user.cadastro.view.CadastrarActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastrarController(val view: CadastrarActivity) {

    fun cadastrar(email: String, senha: String) {
        view.showLoad()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(view, "usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                view.cadastroConcluido()
                view.hideLoad()
            }
        }.addOnFailureListener {
            view.hideLoad()

            val erro = it

            when {
                erro is FirebaseAuthWeakPasswordException -> view.showError("Digite uma senha com no minimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> view.showError("Essa conta ja foi cadastrada")
                erro is FirebaseNetworkException -> view.showError("Sem conexão com a internet")
                else -> view.showError("Erro ao cadastrar usuário")
            }
        }
    }
}

