package com.ncz.desafio_mobile_android.app.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncz.desafio_mobile_android.R
import com.ncz.desafio_mobile_android.app.view.adapters.HomeAdapter
import com.ncz.desafio_mobile_android.app.viewmodel.HomeViewModel
import com.ncz.desafio_mobile_android.databinding.FragmentHomeBinding
import com.ncz.desafio_mobile_android.domain.entities.HomeList
import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.ncz.desafio_mobile_android.domain.utils.Alert
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

    private fun observableCharacters() {
        homeViewModel.liveData.observe(viewLifecycleOwner) { character ->
            when (character.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    character.data?.let { setCharacters(it) }
                }
                Status.ERROR -> {
                    hideLoading()
                    Alert().alertError(
                        requireActivity(),
                        "Aviso",
                        "Ops Parece que ocorreu uma falha inesparada, tente novamente daqui alguns minutos :)",
                        "OK"
                    ) { _, _ -> }
                    print("${character.data}")
                }
                else -> {
                    hideLoading()
                    Alert().alertError(
                        requireActivity(),
                        "Ops",
                        "Ops Parece que ocorreu uma falha.Tente novamente daqui alguns minutos :)",
                        "OK"
                    ) { _, _ -> }
                    print("${character.data}")
                }
            }
        }
    }

    private fun setCharacters(character: List<Character>) {
        val heroes = homeViewModel.getFiveHeroes(character)
        val characters = homeViewModel.getCharacters(character)

        binding.recyclerHome.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )

        binding.recyclerHome.adapter = HomeAdapter(HomeList(heroes, characters))
    }

    private fun showLoading() {
        binding.shimmerView.shimmerViewContainer.isVisible = true
        binding.shimmerView.shimmerViewContainer.startShimmer()
    }

    private fun hideLoading() {
        binding.shimmerView.shimmerViewContainer.stopShimmer()
        binding.shimmerView.shimmerViewContainer.isVisible = false
    }

}