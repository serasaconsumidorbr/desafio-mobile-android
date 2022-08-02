package com.developer.marvel.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.developer.marvel.app.modules.account.AccountFragment
import com.developer.marvel.app.modules.home.HomeFragment
import com.developer.marvel.app.modules.library.LibraryFragment
import com.developer.marvel.app.modules.search.SearchFragment

open class BaseFragment : Fragment() {

    internal val navController: NavController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    fun hideBottomNavigation() {
        (activity as? BaseActivity)?.hideBottomNavigation()
    }

    private fun setupBottomNavigation() {
        if (activity !is BaseActivity) return

        when (this) {
            is HomeFragment -> (activity as BaseActivity).showBottomNavigation()
            is SearchFragment -> (activity as BaseActivity).showBottomNavigation()
            is LibraryFragment -> (activity as BaseActivity).showBottomNavigation()
            is AccountFragment -> (activity as BaseActivity).showBottomNavigation()
            else -> hideBottomNavigation()
        }
    }

}