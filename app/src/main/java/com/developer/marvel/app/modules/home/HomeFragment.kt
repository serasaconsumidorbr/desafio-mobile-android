package com.developer.marvel.app.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.marvel.R
import com.developer.marvel.app.BaseFragment
import com.developer.marvel.app.modules.home.helper.MarginItemDecoration
import com.developer.marvel.app.utils.Snapshot
import com.developer.marvel.databinding.FragmentHomeBinding
import com.developer.marvel.domain.entities.Character
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    private val topCharactersAdapter: CharactersAdapter = CharactersAdapter()
    private val popularCharactersAdapter: CharactersAdapter = CharactersAdapter()

    private var timerTask: TimerTask? = null
    private var timer: Timer? = null
    private var topCharactersCurrentIndex = -1
    private var page = 1

    private var loadingProgressView: Snackbar? = null

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
        binding.recyclerTopCharacters.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
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
            if(loadingProgressView == null) {
                if (scrollView.getChildAt(0).bottom <= (scrollView.height + scrollView.scrollY)) {
                    page++
                    homeViewModel.getCharacters(page = page)
                }
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
            when (it) {
                is Snapshot.Loading -> if(page == 1) showShimmerLoading() else showLoading()

                is Snapshot.Success -> {
                    if(page == 1) hideShimmerLoading() else hideLoading()
                    setupTopCharacters(it.data)
                }
            }
        }
    }


    private fun observePopularCharacters() {
        homeViewModel.popularCharacters.observe(viewLifecycleOwner) {
            when (it) {
                is Snapshot.Loading -> if(page == 1) showShimmerLoading() else showLoading()

                is Snapshot.Success -> {
                    if(page == 1) hideShimmerLoading() else hideLoading()
                    setupPopularCharacters(it.data)
                }

                is Snapshot.Failure -> {
                    if(page == 1) hideShimmerLoading() else hideLoading()

                    page--

                    val snackbar = Snackbar.make(
                        requireView(), it.exception.message ?: "Houve uma falha inesperada",
                        Snackbar.LENGTH_LONG
                    )

                    val snackBarView = snackbar.view
                    snackBarView.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.ic_launcher_background
                        )
                    )
                    snackbar.show()
                }
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

    private fun showShimmerLoading() {
        binding.shimmerHomeView.shimmerHomeContainer.isVisible = true
        binding.shimmerHomeView.shimmerHomeContainer.startShimmer()
    }

    private fun hideShimmerLoading() {
        binding.shimmerHomeView.shimmerHomeContainer.isVisible = false
        binding.shimmerHomeView.shimmerHomeContainer.stopShimmer()
    }

    private fun showLoading() {
        loadingProgressView = Snackbar.make(
            requireView(), "Estamos trabalhando...",
            Snackbar.LENGTH_LONG
        )

        val snackBarView = loadingProgressView?.view
        snackBarView?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.md_theme_light_secondary,
            )
        )
        loadingProgressView?.show()
    }

    private fun hideLoading() {
        loadingProgressView?.dismiss()
        loadingProgressView = null
    }

    private fun startCarousel(
        recyclerTopCharacters: RecyclerView,
        adapter: RecyclerView.Adapter<*>
    ) {
        if (timer != null) return

        timerTask = object : TimerTask() {
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