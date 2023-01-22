package com.example.feature.hero_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.hero_list.databinding.HeroListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HeroListFragment : Fragment(R.layout.hero_list_fragment) {

    private val viewModel: HeroListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HeroListFragmentBinding.inflate(inflater, container, false)

        binding.bindState(viewModel.uiState)

        return binding.root
    }

    private fun HeroListFragmentBinding.bindState(uiState: StateFlow<HeroViewState>) {

        bindCarousel(
            uiState = uiState
        )
    }

    private fun HeroListFragmentBinding.bindCarousel(uiState: StateFlow<HeroViewState>) {

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val carousel = this.heroList
        carousel.layoutManager = layoutManager
        carousel.adapter


        val carouselData = uiState.map {
            (it as HeroViewState.Success).heroes
        }.distinctUntilChanged()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                carouselData.collectLatest {

                }
            }
        }
    }
}