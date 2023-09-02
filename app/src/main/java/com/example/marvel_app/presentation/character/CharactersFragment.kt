package com.example.marvel_app.presentation.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvel_app.databinding.FragmentCharactersBinding
import com.example.marvel_app.framework.imageloader.ImageLoader
import com.example.marvel_app.presentation.character.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

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