package dev.ribeiro.bruno.desafioandroid.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ribeiro.bruno.desafioandroid.presentation.adapter.CarouselAdapter
import dev.ribeiro.bruno.desafioandroid.presentation.adapter.CharacterAdapter
import dev.ribeiro.bruno.desafioandroid.presentation.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dev.ribeiro.bruno.desafioandroid.core.LoadingState
import dev.ribeiro.bruno.desafioandroid.core.State
import dev.ribeiro.bruno.desafioandroid.databinding.FragmentHomeBinding
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var characterAdapter: CharacterAdapter
    private var listCarousel = mutableListOf<CharacterDetail>()
    private var count = 1

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
        initObservers()
        viewModel.getAllCharacters()
        setupList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.loadingState.observe(viewLifecycleOwner){state ->
            when(state) {
                is LoadingState.ShowLoading -> {
                    showLoading()
                }
                is LoadingState.HideLoading -> {
                    hideLoading()
                }
            }
        }
        viewModel.charactersState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Success -> {
                    setupView(state.result)
                }
                is State.Error -> {
                    view?.let { Snackbar.make(it, "Não foi possível se conectar.", Snackbar.LENGTH_LONG).show() }
                }
            }
        }
    }

    private fun setupList() {
        characterAdapter = CharacterAdapter(){
            showLoading()
            if(count < 5){
                listCarousel.add(it)
                count++
            }else{
                setupCarousel()
                binding.carousel.refresh()
                hideLoading()
            }
        }
        binding.recycleViewCharacters.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }
    }

    private fun setupView(it: PagingData<CharacterDetail>) {
        lifecycleScope.launch {
            characterAdapter.submitData(it)
        }
    }

    private fun setupCarousel() {
        val numImages = listCarousel.size
        binding.carousel.setAdapter(CarouselAdapter(numImages, listCarousel).adapter)
    }

    private fun showLoading() = if (binding.loading.visibility == View.GONE) binding.loading.visibility = View.VISIBLE else null

    private fun hideLoading() = if (binding.loading.visibility == View.VISIBLE) binding.loading.visibility = View.GONE else null
}