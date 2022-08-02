package com.developer.marvel.app.modules.account

import androidx.lifecycle.ViewModel
import com.developer.marvel.R

class MaintenanceViewModel : ViewModel() {

    private val backgrounds = listOf(
        R.mipmap.maintenance,
        R.mipmap.maintenance_2,
        R.mipmap.maintenance_3,
    )

    fun getRandomBackground(): Int {
        return backgrounds.shuffled().last()
    }
}