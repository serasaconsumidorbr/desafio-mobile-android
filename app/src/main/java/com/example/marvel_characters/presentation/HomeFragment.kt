package com.example.marvel_characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.marvel_characters.core.helper.EndlessScrollListener
import com.example.marvel_characters.databinding.FragmentHomeBinding
import com.example.marvel_characters.presentation.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw Exception("Binding not initialized")
    private val adapter = HomeAdapter()
    private val endlessScrollListener: EndlessScrollListener by lazy {
        EndlessScrollListener { viewModel.setEvent(HomeViewModel.Event.LoadMore) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characters.adapter = adapter
        binding.characters.addOnScrollListener(endlessScrollListener)
        binding.error.registerButtonAction { viewModel.setEvent(HomeViewModel.Event.Load) }
        viewModel.output.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeViewModel.State.Error -> showError()
                is HomeViewModel.State.Content -> showContent(state)
                is HomeViewModel.State.Loading -> showLoading()
                is HomeViewModel.State.EndOfList -> removeEndlessScrollListener()
            }
        }
    }

    private fun showError() {
        binding.error.visibility = VISIBLE
        binding.loading.visibility = GONE
    }

    private fun showContent(content: HomeViewModel.State.Content) {
        binding.error.visibility = GONE
        binding.loading.visibility = GONE
        adapter.submitList(content.items)
        adapter.notifyItemChanged(3)
        endlessScrollListener.finishLoading()
    }

    private fun showLoading() {
        binding.error.visibility = GONE
        binding.loading.visibility = VISIBLE
    }

    private fun removeEndlessScrollListener() {
        binding.characters.removeOnScrollListener(endlessScrollListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}