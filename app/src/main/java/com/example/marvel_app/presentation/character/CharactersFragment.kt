package com.example.marvel_app.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.marvel_app.databinding.FragmentCharactersBinding
import com.example.marvel_app.framework.imageloader.ImageLoader
import com.example.marvel_app.presentation.character.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterAdapter()

        //faz o stop do flow assim que ele for background
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.charactersPagingData("").collect{ pagingData ->
                    characterAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initCharacterAdapter() {
        characterAdapter = CharacterAdapter(imageLoader)
        binding.rcvCharacters.run {
            setHasFixedSize(true)
            adapter = characterAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}