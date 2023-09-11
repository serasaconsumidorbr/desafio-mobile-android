package com.desafio.marvelapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desafio.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersAdapter

    private lateinit var charactersAdapter2: CharactersAdapter2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharactersAdapter()
        observeInitialLoadState()
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.charactersPagingData("").collect{ pagingData ->
                    charactersAdapter.submitData(pagingData)
                }
            }
        }

        viewModel.character.observe(viewLifecycleOwner) {
            charactersAdapter2.submitList(it.toMutableList())
        }

    }

    fun initCharactersAdapter(){

        charactersAdapter2 = CharactersAdapter2()
        with(binding.recycleCarouselCharacters) {
            scrollToPosition(0)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = charactersAdapter2
        }

        charactersAdapter = CharactersAdapter()
        with(binding.recycleCharacters){
            scrollToPosition(0)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = charactersAdapter.withLoadStateFooter(
                footer = CharactersLoadStateAdapter (
                    charactersAdapter::retry
                )
            )

        }
    }

    private fun observeInitialLoadState(){
        lifecycleScope.launch {
            charactersAdapter.loadStateFlow.collect{ loadState->
                binding.flipperCharacters.displayedChild = when(loadState.refresh) {
                    is LoadState.Loading -> {
                        setShimmerVisibitity(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        setShimmerVisibitity(false)
                        FLIPPER_CHILD_CHARACTERS
                    }
                    else ->  {
                        setShimmerVisibitity(false)
                        binding.includeViewCharactersErrorState.buttonRetry.setOnClickListener {
                            charactersAdapter.refresh()
                        }
                        FLIPPER_CHILD_ERROR
                    }
                }
            }

            /*charactersAdapter2.loadStateFlow.collect{ loadState->
                binding.flipperCharacters.displayedChild = when(loadState.refresh) {
                    is LoadState.Loading -> {
                        setShimmerVisibitity(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        setShimmerVisibitity(false)
                        FLIPPER_CHILD_CHARACTERS
                    }
                    else ->  {
                        setShimmerVisibitity(false)
                        binding.includeViewCharactersErrorState.buttonRetry.setOnClickListener {
                            charactersAdapter2.refresh()
                        }
                        FLIPPER_CHILD_ERROR
                    }
                }
            }*/
        }
    }

    private fun setShimmerVisibitity(visibility: Boolean){
        binding.includeViewCharactersLoadingState.shimmerCharacters.run {
            isVisible = visibility
            if (visibility){
                startShimmer()
            } else stopShimmer()
        }
    }

    companion object{
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_CHARACTERS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }
}