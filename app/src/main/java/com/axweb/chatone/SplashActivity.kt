package com.axweb.chatone

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.axweb.chatone.main.view.MainActivity
import com.axweb.chatone.user.login.view.FormLoginActivity

class SplashActivity : AppCompatActivity() {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            openScreen()
        }, 2000)
    }

    private fun openScreen() {
        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}