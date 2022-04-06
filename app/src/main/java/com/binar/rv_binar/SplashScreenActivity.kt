package com.binar.rv_binar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPreferences: SharedPreferences =
                this.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

            val login_stat = sharedPreferences.getInt("login", 0)

            if (login_stat.equals(0)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 1500)
    }
}