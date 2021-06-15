package com.luisedu.marvel_app.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.luisedu.marvel_app.R
import com.luisedu.marvel_app.utils.ViewModelFactory
import com.luisedu.marvel_app.utils.changeVisibility
import com.luisedu.marvel_app.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.carousel_heros_view_pager.*


class HomeActivity : AppCompatActivity() {

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory()).get(HomeViewModel::class.java)
    }

    private lateinit var homeHeroListAdapter: HomeHeroListAdapter
    private var homeHeroCarouselAdapter = HomeHeroCarouselAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        screenSetup()
    }

    private fun screenSetup() {
        setHeroList()
        setCarouselHeroes()
        setObervables()
        setCloseButtonListener()
        homeViewModel.fetchCharactersList()
    }

    private fun setHeroList() {
        rvHeroList.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            homeHeroListAdapter = HomeHeroListAdapter()
            adapter = homeHeroListAdapter
        }
    }

    private fun setCarouselHeroes() {
        vpHeroes.adapter = homeHeroCarouselAdapter
    }

    private fun observableCharacters() {
        homeViewModel.marvelCharacterResponse.observe(this, Observer { response ->
            if (response != null) {
                homeHeroListAdapter.run {
                    addCharactersList(response)
                    notifyDataSetChanged()
                }
                homeHeroCarouselAdapter.run {
                    addCharactersList(response)
                    notifyDataSetChanged()
                }
            } else {
                showError(true)
            }
        })
    }

    private fun observableError() {
        homeViewModel.errorLiveData.observe(this, Observer {
            showError(true)
        })
    }

    private fun observableLoading() {
        homeViewModel.loading.observe(this, Observer {
            when (it) {
                is HomeViewModel.Loading.ShowLoading -> showLoading(true)
                is HomeViewModel.Loading.HideLoading -> showLoading(false)
            }
        })
    }

    private fun setObervables() {
        observableCharacters()
        observableError()
        observableLoading()
    }

    private fun showLoading(visible: Boolean) {
        ivLoading.changeVisibility(visible)
        setLoadingGif(ivLoading)
    }

    private fun setLoadingGif(view: View) {
        Glide.with(view)
                .asGif()
                .load(R.drawable.infinity_gauntlet_loading)
                .into(ivLoading)
    }

    private fun setCloseButtonListener() {
        ivClose.setOnClickListener{
            this.finish()
        }
    }

    private fun showError(visible: Boolean) {
        setErrorGif(clError)
        clError.changeVisibility(visible)
        clError.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun setErrorGif(view: View) {
        Glide.with(view)
                .asGif()
                .load(R.drawable.thanos_wins_error)
                .into(ivError)
    }
}