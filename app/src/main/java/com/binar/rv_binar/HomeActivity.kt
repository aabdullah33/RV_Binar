package com.binar.rv_binar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binar.rv_binar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val uname = sharedPreferences.getString("uname", "defaultUname")

        binding.tes.text = "Hi $uname"
    }
}