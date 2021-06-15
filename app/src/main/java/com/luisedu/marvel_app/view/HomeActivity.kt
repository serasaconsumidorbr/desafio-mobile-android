package com.luisedu.marvel_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisedu.marvel_app.R
import com.luisedu.marvel_app.utils.ViewModelFactory
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

        setHeroList()
        setCarouselHeroes()
        setObervables()
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
                //TODO show error
            }
        })
    }

    private fun observableError() {
        homeViewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show()
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
//        observableLoading()
    }

    private fun showLoading(visibility: Boolean) {
        //TODO implement loading
    }
}