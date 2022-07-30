package com.developer.marvel.app.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.developer.marvel.app.home.BaseActivity
import com.developer.marvel.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler by lazy {
        Handler(mainLooper)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

//        handler.postDelayed({
//            startActivity(Intent(this, BaseActivity::class.java))
//            finish()
//        }, 1500)
    }

}