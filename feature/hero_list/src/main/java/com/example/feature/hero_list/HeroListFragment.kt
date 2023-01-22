package com.example.feature.hero_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.size
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
        bindVerticalList(uiState = uiState)
    }

    private fun HeroListFragmentBinding.bindCarousel(uiState: StateFlow<HeroViewState>) {

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val carousel = this.carouselHero
        val adapter = CarouselAdapter()
        carousel.layoutManager = layoutManager
        carousel.adapter = adapter


        val carouselData = uiState.filter {
            it is HeroViewState.Success
        }.distinctUntilChanged()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                carouselData.collectLatest {
                    adapter.updateItems((it as HeroViewState.Success).page.hero.take(5))
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private fun HeroListFragmentBinding.bindVerticalList(uiState: StateFlow<HeroViewState>) {

        val list = this.heroList
        var page = 0
        var size = 0

        val layoutManager =
            LinearLayoutManager(requireContext())

        val adapter = VerticalListAdapter()
        list.adapter = adapter
        list.layoutManager = layoutManager

        val isLoading = uiState.filter {
            it is HeroViewState.Loading
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                isLoading.collectLatest {
                    heroesProgressbar.visibility = View.VISIBLE
                }
            }
        }

        val listData = uiState.filter {
            it is HeroViewState.Success
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                listData.collectLatest {
                    val result = (it as HeroViewState.Success)
                    page = result.page.nextPage
                    size = result.page.hero.drop(5).size
                    adapter.updateItems(result.page.hero.drop(5))
                    heroesProgressbar.visibility = View.GONE
                }
            }
        }
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                ////It checks, fully visible view is the last one.
                val rvSize = layoutManager.findLastCompletelyVisibleItemPosition()
                if (rvSize == size - 1) {
                    viewModel.loadHeroes(page)
                }

            }
        })
    }
}