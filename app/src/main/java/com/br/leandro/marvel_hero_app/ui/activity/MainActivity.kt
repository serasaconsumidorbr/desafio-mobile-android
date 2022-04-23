package com.fernandohbrasil.marvelsquad.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.fernandohbrasil.marvelsquad.databinding.ActivityMainBinding
import com.fernandohbrasil.marvelsquad.ui.viewmodel.activity.SharedViewModel
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