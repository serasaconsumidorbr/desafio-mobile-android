package com.example.marvel.controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.R

class SplashActivity : AppCompatActivity() {

    lateinit var imageLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        imageLogo = findViewById(R.id.iv_logo_marvel)

        val intent: Intent =   Intent(this, MainActivity::class.java)

        imageLogo.postDelayed(Runnable {
            startActivity(intent)
            finish()
        }, 3000)
    }
}