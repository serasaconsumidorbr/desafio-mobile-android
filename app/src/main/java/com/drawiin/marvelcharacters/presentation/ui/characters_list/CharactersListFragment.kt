package com.drawiin.marvelcharacters.presentation.ui.characters_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.drawiin.marvelcharacters.databinding.FragmentCharactersListBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {
    private lateinit var binding: FragmentCharactersListBinding

    private val viewModel: CharactersListViewModel by viewModels()

    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var characterListAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        setupUi()
        subscribeUi()
        return binding.root
    }

    private fun setupUi() {
        carouselAdapter = CarouselAdapter()
        binding.carousel.adapter = carouselAdapter
        TabLayoutMediator(binding.carouselIndicator, binding.carousel) { _, _ -> }.attach()

        characterListAdapter = CharactersAdapter()
        binding.charactersList.adapter = characterListAdapter
    }

    private fun subscribeUi() {
        viewModel.charactersCarousel.observe(viewLifecycleOwner) {
            carouselAdapter.submitList(it)
        }

        viewModel.charactersList.observe(viewLifecycleOwner) {
            characterListAdapter.submitList(it)
        }
    }
}