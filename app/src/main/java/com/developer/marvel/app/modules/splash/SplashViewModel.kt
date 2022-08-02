package com.developer.marvel.app.modules.splash

import androidx.lifecycle.ViewModel
import com.developer.marvel.R

class SplashViewModel : ViewModel() {

    private val backgrounds = listOf(
        R.mipmap.splash_3,
        R.mipmap.splash_1,
        R.mipmap.splash_2,
        R.mipmap.splash_4,
        R.mipmap.splash_5,
        R.mipmap.splash_6,
        R.mipmap.splash_10,
        R.mipmap.splash_11,
        R.mipmap.splash_12,
        R.mipmap.splash_13,
        R.mipmap.splash_14,
    )

    fun getRandomBackground(): Int {
        return backgrounds.shuffled().last()
    }
}