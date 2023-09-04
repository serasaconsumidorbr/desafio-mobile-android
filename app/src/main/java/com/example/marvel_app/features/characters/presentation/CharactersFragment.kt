package com.example.marvel_app.features.characters.presentation

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
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.marvel_app.databinding.FragmentCharactersBinding
import com.example.marvel_app.features.characters.presentation.adapter.CharacterAdapter
import com.example.marvel_app.features.characters.presentation.adapter.CharactersLoadStateAdapter
import com.example.marvel_app.framework.imageloader.ImageLoader
import com.example.marvel_app.utils.Constants.FLIPPER_CHILD_CHARACTERS
import com.example.marvel_app.utils.Constants.FLIPPER_CHILD_ERROR
import com.example.marvel_app.utils.Constants.FLIPPER_CHILD_LOADING
import com.example.marvel_app.utils.args.DetailViewArg
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var characterAdapter: CharacterAdapter

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
        initCharacterAdapter()
        observeInitialLoadState()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.charactersPagingData("").collect { pagingData ->
                    characterAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initCharacterAdapter() {
        characterAdapter = CharacterAdapter(imageLoader) { character, view ->
            val extras = FragmentNavigatorExtras(
                view to character.name
            )

            val directions = CharactersFragmentDirections
                .actionCharactersFragmentToDetailFragment(
                    character.name,
                    DetailViewArg(
                        characterId = character.id,
                        name = character.name,
                        imageUrl = character.imageUrl
                    )
                )

            findNavController().navigate(directions, extras)
        }

        binding.rcvCharacters.run {
            scrollToPosition(0)
            setHasFixedSize(true)
            adapter = characterAdapter.withLoadStateFooter(
                footer = CharactersLoadStateAdapter(
                    characterAdapter::retry
                )
            )
        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            characterAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flippersCharacters.displayedChild = when {
                    loadState.mediator?.refresh is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPPER_CHILD_LOADING
                    }

                    loadState.mediator?.refresh is LoadState.Error
                            && characterAdapter.itemCount == 0 -> {
                        setShimmerVisibility(false)
                        binding.includeViewCharacterErrorState.buttonRetry.setOnClickListener {
                            characterAdapter.retry()
                        }
                        FLIPPER_CHILD_ERROR
                    }

                    loadState.source.refresh is LoadState.NotLoading
                            || loadState.mediator?.refresh is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_CHARACTERS
                    }

                    else -> {
                        setShimmerVisibility(false)
                        FLIPPER_CHILD_CHARACTERS
                    }
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewCharactersLoadingState.shimmerCharacters.run {
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else stopShimmer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}