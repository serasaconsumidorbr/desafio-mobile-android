package com.updeveloped.desafiomarvel.views.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.updeveloped.desafiomarvel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}