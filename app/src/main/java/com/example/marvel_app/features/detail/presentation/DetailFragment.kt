package com.example.marvel_app.features.detail.presentation

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.marvel_app.R
import com.example.marvel_app.databinding.FragmentDetailBinding
import com.example.marvel_app.features.detail.presentation.adapter.DetailParentAdapter
import com.example.marvel_app.framework.imageloader.ImageLoader
import com.example.marvel_app.utils.Constants.FLIPPER_CHILD_POSITION_DETAIL
import com.example.marvel_app.utils.Constants.FLIPPER_CHILD_POSITION_EMPTY
import com.example.marvel_app.utils.Constants.FLIPPER_CHILD_POSITION_ERROR
import com.example.marvel_app.utils.Constants.FLIPPER_CHILD_POSITION_LOADING
import com.example.marvel_app.utils.Constants.FLIPPER_FAVORITE_CHILD_POSITION_LOADING
import com.example.marvel_app.utils.Constants.FLIPPER_FAVORITE_CHILD_POSITION_SUCCESS
import com.example.marvel_app.utils.args.DetailViewArg
import com.example.marvel_app.utils.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    private val args by navArgs<DetailFragmentArgs>()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailViewArg = args.detailViewArg

        binding.imageCharacter.run {
            transitionName = detailViewArg.name
            imageLoader.load(this,detailViewArg.imageUrl)
        }

        setSharedElementTransitionOnEnter()
        observeUiState(detailViewArg)
        observeFavoriteUiState()

        viewModel.getCategories(detailViewArg.characterId)

        binding.imageFavoriteIcon.setOnClickListener {
            viewModel.updateFavorite(detailViewArg)
        }
    }

    private fun observeUiState(detailViewArg: DetailViewArg) {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.flipperDetail.displayedChild = when (uiState) {
                DetailViewModel.UiState.Loading -> FLIPPER_CHILD_POSITION_LOADING
                is DetailViewModel.UiState.Success -> {
                    binding.recyclerParentDetail.run{
                        setHasFixedSize(true)
                        adapter = DetailParentAdapter(
                            uiState.detailParentList,
                            imageLoader
                        )
                    }
                    FLIPPER_CHILD_POSITION_DETAIL
                }
                DetailViewModel.UiState.Error -> {
                    binding.includeErrorView.buttonRetry.setOnClickListener {
                        viewModel.getCategories(detailViewArg.characterId)
                    }

                    FLIPPER_CHILD_POSITION_ERROR
                }
                DetailViewModel.UiState.Empty -> FLIPPER_CHILD_POSITION_EMPTY
            }
        }
    }

    private fun observeFavoriteUiState() {
        viewModel.favoriteUiState.observe(viewLifecycleOwner) { favoriteUiState ->
            binding.flipperFavorite.displayedChild = when (favoriteUiState) {
                DetailViewModel.FavoriteUiState.Loading -> {
                    FLIPPER_FAVORITE_CHILD_POSITION_LOADING
                }

                is DetailViewModel.FavoriteUiState.FavoriteIcon -> {
                    binding.imageFavoriteIcon.setImageResource(favoriteUiState.icon)
                    FLIPPER_FAVORITE_CHILD_POSITION_SUCCESS
                }

                is DetailViewModel.FavoriteUiState.Error -> {
                    binding.root.showSnackBar(
                        R.string.error_add_favorite,
                        R.color.red_400
                    )
                    FLIPPER_FAVORITE_CHILD_POSITION_LOADING
                }
            }
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
                sharedElementEnterTransition = this
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}