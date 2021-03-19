package com.drawiin.myheroes.presentation.ui.heroes_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.drawiin.myheroes.databinding.FragmentHeroesBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesFragment : Fragment() {
    private lateinit var binding: FragmentHeroesBinding

    private val viewModel: HeroesViewModel by viewModels()

    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        setupUi()
        subscribeUi()
        return binding.root
    }

    private fun setupUi() {
        carouselAdapter = CarouselAdapter()
        binding.carousel.adapter = carouselAdapter
        TabLayoutMediator(binding.carouselIndicator, binding.carousel) { _, _ -> }.attach()
    }

    private fun subscribeUi() {
        viewModel.heroes.observe(viewLifecycleOwner) {
            carouselAdapter.submitList(it)
        }
    }
}