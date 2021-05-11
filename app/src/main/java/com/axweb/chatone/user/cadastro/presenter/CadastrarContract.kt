package com.axweb.chatone.user.cadastro.presenter

interface CadastrarContract {

    interface  View{
        fun showLoad()
        fun hideLoad()
    }
    interface Presenter {
        fun  CadastrarController()
        fun cadastrarUsuario()
        fun cadastrar(email: String, senha: String)
    }
}