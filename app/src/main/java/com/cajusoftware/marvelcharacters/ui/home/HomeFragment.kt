package com.cajusoftware.marvelcharacters.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.cajusoftware.marvelcharacters.databinding.FragmentHomeBinding
import com.cajusoftware.marvelcharacters.ui.adapters.CharacterListAdapter
import com.cajusoftware.marvelcharacters.ui.home.HomeFragmentDirections.Companion.actionHomeFragmentToCharacterDetailFragment
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val characterViewModel: CharacterViewModel by inject()

    private val navController: NavController by lazy { findNavController() }

    private val onItemClickListener: ((Int) -> Unit) = { characterId ->
        navController.navigate(actionHomeFragmentToCharacterDetailFragment(characterId))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        characterViewModel.getCharactersToUpperCarousel()
        characterViewModel.getCharacters()

        binding.brandModelsRecyclerView.adapter = CharacterListAdapter(onItemClickListener)
            .apply { stateFlow(characterViewModel::checkLoadState) }

        binding.upperCarouselView.onItemClickListener = onItemClickListener

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = characterViewModel

        return binding.root
    }
}