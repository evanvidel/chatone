package com.axweb.chatone

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.axweb.chatone.main.view.MainActivity
import com.axweb.chatone.user.cadastro.view.RegisterSetupActivity
import com.axweb.chatone.user.login.view.FormLoginActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = FirebaseAuth.getInstance()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent2 = Intent(this, RegisterSetupActivity::class.java)
            startActivity(intent2)
            openScreen()
        }, 2000)
    }

    private fun openScreen() {
        val currentUser = auth.currentUser
        val intent = Intent(this, FormLoginActivity::class.java)
        val intent2 = Intent(this, MainActivity::class.java)

        if (currentUser != null){
            startActivity(intent2)
            //startActivity(intent)
        }else {
            startActivity(intent)
        }

        finishAffinity()
    }
}