package com.developer.marvel.app.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.marvel.app.BaseFragment
import com.developer.marvel.app.modules.home.helper.MarginItemDecoration
import com.developer.marvel.app.utils.Snapshot
import com.developer.marvel.databinding.FragmentHomeBinding
import com.developer.marvel.domain.entities.Character
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment: BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    private val topCharactersAdapter: CharactersAdapter = CharactersAdapter()
    private val popularCharactersAdapter: CharactersAdapter = CharactersAdapter()

    private var timerTask: TimerTask? = null
    private var timer: Timer? = null
    private var topCharactersCurrentIndex = -1
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupInterfaces()
        setupObservables()

        homeViewModel.getCharacters(page)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        timerTask?.cancel()
        timerTask = null
        timer?.cancel()
        timer = null
    }

    //region Interfaces
    private fun setupInterfaces() {
        setupUITopCharacters()
        setupUIPopularCharacters()
    }

    private fun setupUITopCharacters() {
        binding.recyclerTopCharacters.adapter = topCharactersAdapter
        binding.recyclerTopCharacters.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerTopCharacters.itemAnimator = DefaultItemAnimator()
        binding.indicatorTopCharacters.attachToRecyclerView(binding.recyclerTopCharacters)
    }

    private fun setupUIPopularCharacters() {
        binding.recyclerPopularCharacters.adapter = popularCharactersAdapter
        binding.recyclerPopularCharacters.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerPopularCharacters.addItemDecoration(MarginItemDecoration(8))
        binding.recyclerPopularCharacters.itemAnimator = DefaultItemAnimator()

        binding.nestedScrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollView = binding.nestedScrollView
            if (scrollView.getChildAt(0).bottom <= (scrollView.height + scrollView.scrollY)) {
                page++
                homeViewModel.getCharacters(page = page)
            }
        }
    }
    //endregion

    //region Observables
    private fun setupObservables() {
        observeTopCharacters()
        observePopularCharacters()
    }

    private fun observeTopCharacters() {
        homeViewModel.topCharacters.observe(viewLifecycleOwner) {
            when(it) {
                is Snapshot.Loading -> TODO()
                is Snapshot.Success -> {
                    setupTopCharacters(it.data)
                }
                is Snapshot.Failure -> TODO()

            }
        }
    }


    private fun observePopularCharacters() {
        homeViewModel.popularCharacters.observe(viewLifecycleOwner) {
            when(it) {
                is Snapshot.Loading -> TODO()
                is Snapshot.Success -> {
                    setupPopularCharacters(it.data)
                }
                is Snapshot.Failure -> TODO()
            }
        }
    }
    //endregion

    //region Setups
    private fun setupTopCharacters(characters: List<Character>) {
        topCharactersAdapter.addAllCharacters(characters)
        startCarousel(binding.recyclerTopCharacters, topCharactersAdapter)
    }

    private fun setupPopularCharacters(characters: List<Character>) {
        popularCharactersAdapter.addAllCharacters(characters)
    }
    //endregion

    private fun startCarousel(
        recyclerTopCharacters: RecyclerView,
        adapter:  RecyclerView.Adapter<*>
    ) {
        if(timer != null) return

        timerTask = object: TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    if (topCharactersCurrentIndex == adapter.itemCount) {
                        topCharactersCurrentIndex = -1
                    }

                    topCharactersCurrentIndex++
                    recyclerTopCharacters.scrollToPosition(topCharactersCurrentIndex)
                }
            }
        }

        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 0, 4000)
    }

}