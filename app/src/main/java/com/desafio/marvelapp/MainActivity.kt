package com.desafio.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.desafio.marvelapp.R
import com.desafio.marvelapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottonNavMain.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.charactersFragment)
        )

        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination)
                binding.toolbarApp.setNavigationIcon(R.drawable.ic_back)
        }
    }
}