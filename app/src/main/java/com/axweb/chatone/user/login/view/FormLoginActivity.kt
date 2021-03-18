package com.axweb.chatone.user.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.user.cadastro.view.CadastrarActivity
import com.axweb.chatone.databinding.ActivityFormLoginBinding
import com.axweb.chatone.main.view.MainActivity
import com.axweb.chatone.user.login.presenter.LoginContract
import com.axweb.chatone.user.login.presenter.LoginPresenter
import com.axweb.chatone.user.perfil.view.PerfilActivity
import kotlinx.android.synthetic.main.activity_form_login.*

class FormLoginActivity : AppCompatActivity(), LoginContract.View {

    val presenter = LoginPresenter(this)

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.textEmail.text.toString()
            val senha = binding.textPassword.text.toString()


            if (email.isEmpty() || senha.isEmpty()) {
                showError("prencha todos os campos")

            } else {

                autenticarUsuario()
            }
        }
        text_cadastrar.setOnClickListener {
            cadastrarUsuario()
        }
    }

    override fun onDestroy() {
        presenter.view = null
        super.onDestroy()
    }

    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    override fun redirect() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    override fun showError(msg: String) {
        binding.textError.setText(msg)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun autenticarUsuario() {
        val email = binding.textEmail.text.toString()
        val senha = binding.textPassword.text.toString()
        presenter.loginUser(email, senha)
    }

    private fun cadastrarUsuario() {
        val intent = Intent(this, CadastrarActivity::class.java)
        startActivity(intent)
    }
}