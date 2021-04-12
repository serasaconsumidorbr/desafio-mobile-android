package com.desafio.marvel.feature.characters.ui.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafio.marvel.R
import com.desafio.marvel.commons.api.ApiClient
import com.desafio.marvel.commons.utils.Constants
import com.desafio.marvel.feature.characters.data.datasource.CharactersLocalDataSource
import com.desafio.marvel.feature.characters.data.repository.CharactersRepositoryImpl
import com.desafio.marvel.feature.characters.domain.interactor.CharactersInteractorImpl
import com.desafio.marvel.feature.characters.domain.model.CharactersResponse
import com.desafio.marvel.feature.characters.ui.adapter.CharactersAdapter
import com.desafio.marvel.feature.characters.ui.presenter.CharactersPresenterImpl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_characters.*
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class CharactersActivity : CharactersView, AppCompatActivity() {

    private val mCharactersPresenter by lazy {
        CharactersPresenterImpl(this)
    }

    private val mApiClient by lazy {
        ApiClient()
    }

    private val mCharactersRepository by lazy {
        CharactersRepositoryImpl(mApiClient)
    }

    private val mCharactersDataSource by lazy {
        CharactersLocalDataSource(
            getSharedPreferences(
                Constants.USER_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )
        )
    }

    private val mCharactersInteractor by lazy {
        CharactersInteractorImpl(mCharactersRepository, mCharactersPresenter, mCharactersDataSource, this)
    }

    private val mAdapter by lazy {
        CharactersAdapter(mutableListOf())
    }

    var sampleImages = mutableListOf<String>()
    var offset = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)


        setupView()

    }

    override fun onResume() {
        super.onResume()

        startLoading()
        mCharactersInteractor.getCharacters(0)
    }

    private fun setupView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@CharactersActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = mAdapter
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    mCharactersInteractor.getCharacters(offset)
                    offset += 20
                }
            }
        })
    }


    private fun startLoading() {
        name_app.visibility = View.GONE
        view_divider1.visibility = View.GONE
        view_divider2.visibility = View.GONE
        characters_list_progress_bar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        name_app.visibility = View.VISIBLE
        view_divider1.visibility = View.VISIBLE
        view_divider2.visibility = View.VISIBLE
        characters_list_progress_bar.visibility = View.GONE
    }

    override fun showUsers(list: CharactersResponse) {
        stopLoading()
        if ( offset == 20){
            for (i in 0..5) {
                if (!list.results[i].thumbnail.path.contains("image_not_available"))
                    sampleImages.add(list.results[i].thumbnail.path + "." + list.results[i].thumbnail.extension)
            }

            carouselView.setImageListener { position, imageView ->

                Picasso.get().load(sampleImages[position]).into(imageView)
            }
            carouselView.pageCount = sampleImages.size
        }
        mAdapter.updateItems(list.results.subList(6, list.results.size))
    }

    override fun showErrorMessage(message: String) {
        stopLoading()
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(getString(android.R.string.yes))
            .setMessage(message)
            .setPositiveButton(getString(android.R.string.yes)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun showErrorNetwork() {
        stopLoading()
        Toast.makeText(
            this,
            getString(R.string.error_network),
            Toast.LENGTH_LONG
        ).show()
    }


}