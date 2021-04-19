package com.challenge.marvelcharacters.view.characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.challenge.marvelcharacters.R
import com.challenge.marvelcharacters.view.characters.adapters.CharacterPageAdapter
import kotlinx.android.synthetic.main.activity_characters.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class CharactersActivity : AppCompatActivity() {

    private  val  viewModel: CharacterViewModel by viewModel() //CharacterViewModel(CharacterRepository())
    private lateinit var adapter : CharacterPageAdapter
    private var job : Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        loadCharacters()
        configureRecyclerView()
        loadNextCharacters()
        configureObservers()
        configureCarouselView()
    }

    private fun loadCharacters(){
        viewModel.loadFirstCharacters()
    }

    private fun configureRecyclerView(){
        rvCharacters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter =
            CharacterPageAdapter()
        rvCharacters.adapter = adapter

    }

    private fun configureObservers(){
        viewModel.carouselImageList.observe(this, Observer {
                configureCarouselView()
        })

    }

    private fun configureCarouselView(){
        carouselView.pageCount = 5;
        setImageListener()
    }

    private fun setImageListener(){
        carouselView.setImageListener { position, imageView ->

            val imgUrl = viewModel.getCarouselImageUrl(position)
            imgUrl.let {
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(imageView.context)
                    .load(imgUri)
                    .error(R.drawable.ic_broken_image)
                    .into(imageView)
            }
        }
    }

    private fun loadNextCharacters(){
        job?.cancel()
        job= lifecycleScope.launch{
            viewModel.getCharacters().collectLatest {
                adapter.submitData(it)
            }
        }

    }
}