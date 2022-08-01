package com.developer.marvel.app.modules.splash

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.developer.marvel.app.BaseActivity
import com.developer.marvel.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    //https://developer.android.com/topic/libraries/view-binding?hl=pt-br#activities
    private lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val imageResource = viewModel.getRandomBackground()
        binding.splashImageBackground.setImageResource(imageResource)

        Handler(mainLooper).postDelayed(
            {
                startActivity(BaseActivity.newInstance(this))
                finish()
            },
            1500
        )
    }

}