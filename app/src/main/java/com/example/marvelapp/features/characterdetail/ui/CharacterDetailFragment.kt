package com.example.marvelapp.features.characterdetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.marvelapp.MainActivity
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharacterDetailBinding
import com.example.marvelapp.features.characterdetail.data.model.CharacterDetailModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharacterDetailBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideSearchIconToolBar()
        viewModel.getCharacterDetail(args.characterId.toInt())
        setupObserver()
        (requireActivity() as MainActivity).hideBottomNavigation()
    }

    private fun hideSearchIconToolBar() {
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        toolbar?.inflateMenu(R.menu.main_menu)
        toolbar?.menu?.findItem(R.id.search)?.let { menuItem ->
            menuItem.isVisible = false
        }
        toolbar?.menu?.findItem(R.id.favorite)?.let { menuItem ->
            menuItem.isVisible = true
            setMenuClick(menuItem)
        }
        toolbar?.menu?.findItem(R.id.comics)?.let { menuItem ->
            menuItem.isVisible = true
            setMenuClick(menuItem)
        }
    }

    private fun setMenuClick(menuItem: MenuItem) {
        menuItem.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorite -> {
                    Toast.makeText(
                        requireContext(),
                        requireContext().getString(R.string.add_favorites_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.comics -> {
                    val action =
                        CharacterDetailFragmentDirections.actionCharacterDetailsToComicsFragment(
                            args.characterId
                        )
                    findNavController().navigate(action)
                }
            }
            true
        }
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                UiState.Loading -> {
                    showLoading()
                }

                is UiState.Success -> {
                    showDetails(uiState.data)
                }

                UiState.Error -> {
                    showErrorMessage()
                }
            }
        }
    }

    private fun showDetails(data: CharacterDetailModel) {
        with(binding) {
            shimmerLayout.isVisible = false
            txtNotFound.isVisible = false
            imgError.isVisible = false
            txtCharacterName.isVisible = true
            txtCharacterDetail.isVisible = true
            imgCharacter.isVisible = true
            txtCharacterName.text = data.name
            txtCharacterDetail.text =
                if (data.description?.isNotEmpty() == true) {
                    data.description
                } else {
                    requireContext().getString(
                        R.string.no_description
                    )
                }
            imgCharacter.load(data.thumbnail) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }

    private fun showLoading() {
        with(binding) {
            shimmerLayout.isVisible = true
            txtCharacterName.isVisible = false
            txtCharacterDetail.isVisible = false
            imgCharacter.isVisible = false
            imgError.isVisible = false
            txtNotFound.isVisible = false
        }
    }

    private fun showErrorMessage() {
        with(binding) {
            shimmerLayout.isVisible = false
            txtCharacterName.isVisible = false
            txtCharacterDetail.isVisible = false
            imgCharacter.isVisible = false
            imgError.isVisible = true
            txtNotFound.isVisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar?.inflateMenu(R.menu.main_menu)
        toolbar?.menu?.findItem(R.id.favorite)?.let { menuItem ->
            menuItem.isVisible = false
        }
        toolbar?.menu?.findItem(R.id.comics)?.let { menuItem ->
            menuItem.isVisible = false
        }
        _binding = null
    }
}