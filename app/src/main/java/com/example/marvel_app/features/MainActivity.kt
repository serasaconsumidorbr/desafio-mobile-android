package com.example.marvel_app.features

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.marvel_app.R
import com.example.marvel_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavStartDestination(R.id.charactersFragment)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.charactersFragment)
        )

        binding.toolbar.setupWithNavController(navController,appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination
                .id)

            if (!isTopLevelDestination) {
                binding.toolbar.setNavigationIcon(R.drawable.ic_back)
            }
        }
    }

    private fun setNavStartDestination(@IdRes startDestinationId: Int) {
        val navController = getNavHostFragment().navController
        val navGraph = navController.navInflater.inflate(R.navigation.characters)

        navGraph.setStartDestination(startDestinationId)
        navController.graph = navGraph
    }

    private fun getNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
    }
}