package com.axweb.chatone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.axweb.chatone.user.login.view.FormLoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            openScreen()
        },2000)
    }
    private fun openScreen(){
        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}