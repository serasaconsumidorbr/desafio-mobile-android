package com.ncz.desafio_mobile_android.app.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncz.desafio_mobile_android.R
import com.ncz.desafio_mobile_android.app.view.adapters.HomeAdapter
import com.ncz.desafio_mobile_android.app.viewmodel.HomeViewModel
import com.ncz.desafio_mobile_android.databinding.FragmentHomeBinding
import com.ncz.desafio_mobile_android.domain.entities.HomeList
import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.ncz.desafio_mobile_android.domain.utils.Status

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

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
        observableCharacters()
        homeViewModel.getCharacter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     fun observableCharacters() {
        homeViewModel.liveData.observe(viewLifecycleOwner) { character ->
            when (character.status) {
                Status.LOADING -> {
                    binding.shimmerView.shimmerViewContainer.startShimmer()
                }
                Status.SUCCESS -> {
                    binding.shimmerView.shimmerViewContainer.stopShimmer()
                    character.data?.let { setCharacters(it) }
                }
                Status.ERROR -> {
                    binding.shimmerView.shimmerViewContainer.stopShimmer()
                    //Alert de erro
                    print("${character.data}")
                }
                else -> {
                    binding.shimmerView.shimmerViewContainer.stopShimmer()
                    //Alert de erro
                    print("${character.data}")
                }
            }
        }
    }

     private fun setCharacters(character: List<Character>) {
        val heroes = homeViewModel.getFiveHeroes(character)
        val characters = homeViewModel.getCharacters(character)

        binding.recyclerHome.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        binding.recyclerHome.adapter = HomeAdapter(HomeList(heroes, characters))
    }


}