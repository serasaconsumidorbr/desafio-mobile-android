package com.br.leandro.marvel_hero_app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.br.leandro.marvel_hero_app.databinding.ActivityMainBinding
import com.br.leandro.marvel_hero_app.ui.viewmodel.activity.SharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val sharedViewModel: SharedViewModel by viewModel()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedViewModel.titleBar.observe(this, (titleBarObserver()))
    }

    private fun titleBarObserver(): Observer<Boolean> = Observer {
        when (it) {
            true -> supportActionBar?.show()
            false -> supportActionBar?.hide()
        }
    }
}