package com.developer.marvel.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.developer.marvel.R
import com.developer.marvel.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BaseActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val bottomNavigationView: BottomNavigationView = binding.bottomNavView

        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHost?.childFragmentManager?.fragments?.firstOrNull()
        if (fragment is BaseFragment && fragment.onBackPressed()) return

        super.onBackPressed()
    }

    fun showBottomNavigation() {
        val bottomNavigationView: BottomNavigationView = binding.bottomNavView

        bottomNavigationView.isVisible = true
        bottomNavigationView.animate()
            ?.translationY(0f)
            ?.setInterpolator(AccelerateDecelerateInterpolator())
            ?.start()
    }

    fun hideBottomNavigation() {
        val bottomNavigationView: BottomNavigationView = binding.bottomNavView

        bottomNavigationView.isVisible = false
        bottomNavigationView.animate()
            ?.translationY(bottomNavigationView.height.toFloat())
            ?.setInterpolator(AccelerateDecelerateInterpolator())
            ?.start()
    }

    companion object {

        fun newInstance(context: Context): Intent {
            return Intent(context, BaseActivity::class.java)
        }

    }

}