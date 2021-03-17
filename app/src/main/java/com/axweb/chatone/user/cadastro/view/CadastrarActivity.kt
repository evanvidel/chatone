package com.axweb.chatone.user.cadastro.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.user.cadastro.controller.CadastrarController
import com.axweb.chatone.databinding.ActivityCadastrarBinding
import kotlinx.android.synthetic.main.activity_form_login.*

class CadastrarActivity : AppCompatActivity() {

    val controller = CadastrarController(this)

    private lateinit var binding: ActivityCadastrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val nome = binding.textNome.text.toString()
            val email = binding.textEmail.text.toString()
            val senha = binding.password.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                showError("prencha todos os campos")
            } else {
                cadastrarUsuario()
            }
        }
    }

    fun showError(msg: String) {
        binding.textError.setText(msg)
    }

    fun cadastroConcluido() {
        binding.textEmail.setText("")
        binding.password.setText("")
        binding.textError.setText("")
    }

    private fun cadastrarUsuario() {

        val email = binding.textEmail.text.toString()
        val senha = binding.password.text.toString()
        controller.cadastrar(email, senha)

    }

    fun showLoad() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoad() {
        progressBar.visibility = View.GONE
    }

}