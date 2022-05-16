package com.updeveloped.desafiomarvel.views.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.updeveloped.desafiomarvel.core.State
import com.updeveloped.desafiomarvel.databinding.FragmentHomeBinding
import com.updeveloped.desafiomarvel.domain.entities.CharacterDetail
import com.updeveloped.desafiomarvel.helper.DepthPageTransformer
import com.updeveloped.desafiomarvel.views.adapter.CarouselAdapter
import com.updeveloped.desafiomarvel.views.adapter.CharacterAdapter
import com.updeveloped.desafiomarvel.views.viewModel.ListCharactersViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var carouselListAdapter: CarouselAdapter
    private lateinit var characterListAdapter: CharacterAdapter
    private val viewModel: ListCharactersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel.getAllCharacters()
        viewModel.getAllCharactersPage()
        setListCharacters()
    }

    private fun initObservers() {
        viewModel.charactersState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Success -> {
                    setCharactersAdapter(state.result)
                }
                is State.Error -> {
                    view?.let { Snackbar.make(it, "Verifique sua conexão.", Snackbar.LENGTH_LONG).show() }
                }
            }
        }
        viewModel.characters.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Success -> {
                    setCorouselAdapter(state.result)
                }
                is State.Error -> {
                    view?.let { Snackbar.make(it, "Verifique sua conexão.", Snackbar.LENGTH_LONG).show() }
                }
            }
        }
    }

    private fun setListCharacters() {
        characterListAdapter = CharacterAdapter {}
        binding.charactersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterListAdapter
        }
    }

    private fun setCharactersAdapter(it: PagingData<CharacterDetail>) {
        lifecycleScope.launch {
            characterListAdapter.submitData(it)
        }
    }

    private fun setCorouselAdapter(it: List<CharacterDetail>) {
        carouselListAdapter = CarouselAdapter(it)
        binding.carousel.adapter = carouselListAdapter
        binding.carousel.setPageTransformer(DepthPageTransformer())

        binding.carouselIndicator.setViewPager2(binding.carousel)
        //TabLayoutMediator(binding.carouselIndicator, binding.carousel) { _, _ -> }.attach()
    }
}