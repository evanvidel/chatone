package com.axweb.chatone.user.cadastro.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.databinding.ActivityCadastrarBinding
import com.axweb.chatone.user.cadastro.presenter.CadastrarContract
import com.axweb.chatone.user.cadastro.presenter.CadastrarPresenter
import com.axweb.chatone.user.login.view.FormLoginActivity
import kotlinx.android.synthetic.main.activity_cadastrar.*
import kotlinx.android.synthetic.main.activity_form_login.progressBar

class CadastrarActivity : AppCompatActivity(), CadastrarContract.View {

    private val presenter = CadastrarPresenter(this)

  // val controller = CadastrarController(this)

    private lateinit var binding: ActivityCadastrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar_cadastrar.setOnClickListener {
            val intent = Intent(this, FormLoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {


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
        presenter.cadastrar(email, senha)

    }

    override fun showLoad() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoad() {
        progressBar.visibility = View.GONE
    }

    fun goToSetupRegister(){
        val intent = Intent(this, RegisterSetupActivity::class.java)
        startActivity(intent)
    }


}