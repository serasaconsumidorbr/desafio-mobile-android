package com.victorvgc.mymarvelheros.home.ui

import android.os.Bundle
import com.victorvgc.mymarvelheros.R
import com.victorvgc.mymarvelheros.core.ui.BaseActivity
import com.victorvgc.mymarvelheros.databinding.ActivityHomeBinding
import com.victorvgc.mymarvelheros.home.domain.domain.HeroPage
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HeroPage>() {

    private val binding: ActivityHomeBinding by binding(R.layout.activity_home)

    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@HomeActivity
        }
    }
}