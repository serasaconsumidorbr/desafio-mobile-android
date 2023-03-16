package com.cajusoftware.marvelcharacters.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.cajusoftware.marvelcharacters.R
import com.cajusoftware.marvelcharacters.databinding.ActivityMainBinding
import com.cajusoftware.marvelcharacters.utils.setStatusBarColorForUnderApi23
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColorForUnderApi23()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setupActionBarWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        intent.getStringExtra(ERROR_TAG)
            ?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
            ?.also { intent.removeExtra(ERROR_TAG) }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        const val ERROR_TAG = "error_tag"
    }

}