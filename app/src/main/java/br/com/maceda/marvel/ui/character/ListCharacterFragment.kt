package br.com.maceda.marvel.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.maceda.marvel.data.model.Character
import br.com.maceda.marvel.databinding.CharacterFragmentBinding
import br.com.maceda.marvel.ui.adapters.CharacterAdapter
import br.com.maceda.marvel.ui.adapters.CharacterCarouselAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.material.tabs.TabLayoutMediator


@AndroidEntryPoint
class ListCharacterFragment : Fragment() {

    companion object {
        fun newInstance() = ListCharacterFragment()
    }

    private lateinit var binding: CharacterFragmentBinding
    private val viewModelList: ListCharacterViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }
    private val characterCarouselAdapter by lazy { CharacterCarouselAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  CharacterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupAdapter()
        setupCarousel()
        setupError()
        loadCharacters()
    }

    private fun loadCharacters() {
        binding.recyclerView.visibility = View.INVISIBLE
        viewModelList.loadCharacters()
        viewModelList.progressBarVisibility.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModelList.list.observe(viewLifecycleOwner, {
            binding.recyclerView.visibility = View.VISIBLE
            characterAdapter.characters = it
        })

        viewModelList.listFirstCharacters.observe(viewLifecycleOwner, {
            characterCarouselAdapter.characters = it
        })
    }

    private fun setupAdapter() {

        val onItemClick = { character: Character ->
            Toast.makeText(requireContext(), character.name, Toast.LENGTH_SHORT).show()
        }

        characterCarouselAdapter.onItemClickListener = onItemClick
        characterAdapter.onItemClickListener = { character ->
            Toast.makeText(requireContext(), character.name, Toast.LENGTH_SHORT).show()
        }
        characterAdapter.onLastPosition = { position ->
            viewModelList.loadCharacters(offset = position)
        }

    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupCarousel() {
        binding.viewPager.apply {
            adapter = characterCarouselAdapter
            offscreenPageLimit = 5
        }

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ _, _ ->

        }.attach()
    }

    private fun setupError() {
        viewModelList.error.observe(viewLifecycleOwner){ it ->
            val hasError = it.first
            val message = it.second
            binding.viewPager.visibility = if (hasError) View.GONE else View.VISIBLE
            binding.tabLayout.visibility = if (hasError) View.GONE else View.VISIBLE
            binding.recyclerView.visibility = if (hasError) View.GONE else View.VISIBLE
            binding.contentError.visibility = if (hasError) View.VISIBLE else View.GONE
            binding.txtErrorLoading.text = message
            binding.btnTryAgain.setOnClickListener {
                binding.contentError.visibility = View.GONE
                loadCharacters()
            }
        }
    }


}


