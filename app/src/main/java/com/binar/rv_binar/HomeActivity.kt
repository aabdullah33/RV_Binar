package com.binar.rv_binar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binar.rv_binar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val uname = sharedPreferences.getString("uname", "defaultUname")

        binding.txtWelcome.text = "Hi $uname"
        binding.txtLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.remove("login").apply()
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
}