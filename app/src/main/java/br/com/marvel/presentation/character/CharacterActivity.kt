package br.com.marvel.presentation.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import br.com.marvel.databinding.ActivityCharacterBinding
import br.com.marvel.domain.models.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        characterViewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        initCharacters()
    }

    private fun setupCarousel(characterAdapter: CharacterAdapter) {
        lifecycleScope.launch {
            characterAdapter.loadStateFlow.distinctUntilChangedBy {
                it.refresh
            }.collect {
                if (!::carouselAdapter.isInitialized && characterAdapter.snapshot().size > 0) {
                    val carouselList = characterAdapter.snapshot().subList(0, 5)

                    carouselAdapter = CarouselAdapter(carouselList.size, carouselList)

                    binding.apply {
                        carousel.setAdapter(carouselAdapter)
                        carousel.refresh()
                    }
                }
            }
        }
    }

    private fun initCharacters() {
        val characterAdapter = CharacterAdapter()

        binding.apply {
            listCharacter.adapter = characterAdapter

            btnRetry.setOnClickListener {
                characterAdapter.retry()
            }
        }

        lifecycleScope.launch {
            characterViewModel.characters.collectLatest {
                characterAdapter.submitData(it)
            }
        }

        setupCarousel(characterAdapter)

        characterAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progress.isVisible = loadState.source.refresh is LoadState.Loading
                listCharacter.isVisible = loadState.source.refresh is LoadState.NotLoading
                carousel.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                errorMessage.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && characterAdapter.itemCount < 1) {
                    carousel.isVisible = false
                    listCharacter.isVisible = false
                    notFound.isVisible = true
                } else notFound.isVisible = false
            }
        }
    }
}