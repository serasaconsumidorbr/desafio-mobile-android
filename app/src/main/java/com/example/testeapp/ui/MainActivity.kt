package com.example.testeapp.ui

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testeapp.R
import com.example.testeapp.common.BaseActivity
import com.example.testeapp.databinding.ActivityMainBinding
import com.example.testeapp.model.MarvelCharacter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    val characterAdapter = CharacterAdapter(object : OnCharacterItemClickListener {
        override fun onFavoriteClicked(character: MarvelCharacter) {
            viewModel.update(character)
        }
    })

    val carouselAdapter = CharacterAdapter(null)

    override fun observeData() {

        lifecycleScope.launch {
            viewModel.state
                .filter { !it.characterList.isNullOrEmpty() }
                .distinctUntilChanged()
                .collectLatest {
                    it.characterList?.let { newList ->
                        var fullList = (characterAdapter.currentList + newList).distinct()
                        characterAdapter.submitList(fullList)
                        carouselAdapter.submitList(it.topComicAppearance)
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.recyclerViewTitle.visibility = View.VISIBLE
                        binding.carouselTitle.visibility = View.VISIBLE
                        binding.loading.visibility = View.GONE
                        binding.error.visibility = View.GONE
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.state
                .filter { it.characterList.isNullOrEmpty() && it.inProgress == false && it.errorMessage.isNullOrEmpty() }
                .distinctUntilChanged()
                .collectLatest {
                    Toast.makeText(this@MainActivity, getString(R.string.all_loaded), Toast.LENGTH_LONG).show()
                    binding.loading.visibility = View.GONE
                }
        }

        lifecycleScope.launch {
            viewModel.state
                .filter { it.inProgress == true }
                .distinctUntilChanged()
                .collect {
                    binding.loading.visibility = View.VISIBLE
                }
        }

        lifecycleScope.launch {
            viewModel.state
                .filter { !it.errorMessage.isNullOrEmpty() && it.characterList.isNullOrEmpty() }
                .distinctUntilChanged()
                .collectLatest {
                    binding.error.apply {
                        text = getString(R.string.try_again, it.errorMessage)
                        visibility = View.VISIBLE
                        setOnClickListener {
                            viewModel.fetchCharacters()
                        }
                    }
                    binding.loading.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.recyclerViewTitle.visibility = View.GONE
                    binding.carouselTitle.visibility = View.GONE
                }
        }

    }

    override fun setUpUi() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
            val dividerItemDecoration = DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL
            )
            val verticalDivider = ContextCompat.getDrawable(context, R.drawable.divisor)

            if (verticalDivider != null) {
                dividerItemDecoration.setDrawable(verticalDivider)
            }
            addItemDecoration(dividerItemDecoration)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (totalItemCount <= lastVisibleItem + 5) {
                        viewModel.isLoading()
                        viewModel.offset += 20
                        viewModel.fetchCharacters()
                    }
                }
            })
        }

        binding.carousel.apply {
            adapter = carouselAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }


}