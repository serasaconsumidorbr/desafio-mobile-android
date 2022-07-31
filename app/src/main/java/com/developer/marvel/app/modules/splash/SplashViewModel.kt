package com.developer.marvel.app.modules.splash

import androidx.lifecycle.ViewModel
import com.developer.marvel.R

class SplashViewModel: ViewModel() {

    private val backgrounds = listOf(
        R.mipmap.splash_3,
        R.mipmap.splash_1,
        R.mipmap.splash_2,
        R.mipmap.splash_4,
        R.mipmap.splash_5,
        R.mipmap.splash_6,
    )

    fun getRandomBackground(): Int {
        return backgrounds.shuffled().last()
    }
}