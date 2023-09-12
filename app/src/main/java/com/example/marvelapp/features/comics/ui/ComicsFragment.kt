package com.example.marvelapp.features.comics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.marvelapp.MainActivity
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentComicsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    private val args: ComicsFragmentArgs by navArgs()
    private val viewModel: ComicsViewModel by viewModels()

    private lateinit var comicsAdapter: ComicsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentComicsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideBottomNavigation()
        setupAdapter()
        setupObservers()
        clearToolbar()
        setSwipeRefreshLayout()
    }

    private fun setupAdapter() {
        with(binding) {
            comicsAdapter = ComicsAdapter()
            rcvComics.adapter = comicsAdapter
            comicsAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.NotLoading -> {
                        showCharactersList()
                    }

                    is LoadState.Loading -> {
                        showLoading()
                    }

                    is LoadState.Error -> {
                        showError()
                    }
                }
                if (loadState.append.endOfPaginationReached && comicsAdapter.itemCount == 0) {
                    showEmptyListText()
                }
            }
        }
    }

    private fun showLoading() {
        with(binding) {
            shimmerLayout.isVisible = true
            rcvComics.isVisible = false
            imgError.isVisible = false
            txtError.isVisible = false
        }
    }

    private fun showError() {
        with(binding) {
            shimmerLayout.isVisible = false
            rcvComics.isVisible = false
            imgError.isVisible = true
            txtError.isVisible = true
        }
    }

    private fun showEmptyListText() {
        with(binding) {
            shimmerLayout.isVisible = false
            rcvComics.isVisible = false
            imgError.isVisible = true
            txtNotFound.isVisible = true
            txtError.isVisible = false
        }
    }

    private fun showCharactersList() {
        with(binding) {
            shimmerLayout.isVisible = false
            rcvComics.isVisible = true
            imgError.isVisible = false
            txtNotFound.isVisible = false
            txtError.isVisible = false
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getComics(args.characterId.toInt())
                .collect { pagingData ->
                    comicsAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                }
        }
    }

    private fun clearToolbar() {
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        toolbar?.inflateMenu(R.menu.main_menu)
        toolbar?.menu?.findItem(R.id.comics)?.let { menuItem ->
            menuItem.isVisible = false
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.refreshComics.setOnRefreshListener {
            setupObservers()
            binding.refreshComics.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}