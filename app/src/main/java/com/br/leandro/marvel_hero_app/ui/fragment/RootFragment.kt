package com.fernandohbrasil.marvelsquad.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.br.leandro.marvel_hero_app.databinding.FragmentRootBinding
import com.br.leandro.marvel_hero_app.datasource.db.model.CharacterEntity
import com.br.leandro.marvel_hero_app.ui.adapter.CharacterHorizontalAdapter
import com.br.leandro.marvel_hero_app.ui.adapter.CharacterVerticalAdapter
import com.br.leandro.marvel_hero_app.ui.viewmodel.activity.SharedViewModel
import com.br.leandro.marvel_hero_app.ui.viewmodel.fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RootFragment : Fragment() {

    private var _binding: FragmentRootBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<RootViewModel>()
    private val sharedViewModel by sharedViewModel<SharedViewModel>()

    private val verticalAdapter: CharacterVerticalAdapter by lazy {
        CharacterVerticalAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAllCharacters.adapter = verticalAdapter

        sharedViewModel.hasTitleBar(true)

        viewModel.rootCharactersApiState.observe(viewLifecycleOwner, rootCharactersApiStateObserver())
        viewModel.rootMySquadState.observe(viewLifecycleOwner, rootMySquadStateObserver())
    }

    private fun rootMySquadStateObserver(): Observer<RootMySquadState> = Observer { state ->
        when (state) {
            is RootMySquadStarted -> {
                binding.clMySquad.visibility = GONE
                viewModel.findMySquad()
            }
            is RootMySquadSuccess -> {
                if (state.charactersEntity.isNotEmpty()) binding.clMySquad.visibility = VISIBLE
                bindHorizontalAdapter(state.charactersEntity)
            }
            is RootMySquadError -> Toast.makeText(
                requireContext(),
                state.error.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun rootCharactersApiStateObserver(): Observer<RootCharactersApiState> = Observer { state ->
        when (state) {
            is RootCharactersApiSuccess -> verticalAdapter.submitList(state.characters)
            is RootCharactersApiError -> Toast.makeText(
                requireContext(),
                state.error.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun bindHorizontalAdapter(items: MutableList<CharacterEntity>) {
        val horizontalAdapter = CharacterHorizontalAdapter()
        binding.rvMySquad.adapter = horizontalAdapter
        horizontalAdapter.submitList(items)
    }

    override fun onStart() {
        super.onStart()
        viewModel.start()
    }

    override fun onStop() {
        super.onStop()
        viewModel.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}