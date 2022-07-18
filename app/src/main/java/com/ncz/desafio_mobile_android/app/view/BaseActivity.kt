package com.ncz.desafio_mobile_android.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ncz.desafio_mobile_android.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}