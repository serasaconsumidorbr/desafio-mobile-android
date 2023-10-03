package com.marvelverse.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import com.marvelverse.R
import com.marvelverse.databinding.FragmentFavoritesMasterBinding
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.ThumbnailService
import com.marvelverse.ui.MarvelCharactersViewModel
import com.marvelverse.ui.charactermaster.MarvelCharactersRecyclerViewAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    //We use by activityViewModels instead of by viewModels because we want to share the viewmodel
    //between master and detail fragment associated with the activity lifecycle (using by viewmodel
    // it is associated to the fragment lifecycle)
    private val marvelCharactersViewModel: MarvelCharactersViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MarvelCharactersRecyclerViewAdapter
    private lateinit var fragmentFavoritesMasterBinding: FragmentFavoritesMasterBinding

    @Inject
    lateinit var thumbnailService: ThumbnailService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentFavoritesMasterBinding =
            FragmentFavoritesMasterBinding.inflate(inflater, container, false)
        return fragmentFavoritesMasterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentFavoritesMasterBinding.marvelCharactersViewModel = marvelCharactersViewModel
        fragmentFavoritesMasterBinding.lifecycleOwner = this

        val onMarvelCharacterClickListener = View.OnClickListener { itemView ->
            navigateToDetailFragment(itemView)
        }
        setAdapter(listOf(), onMarvelCharacterClickListener)

        marvelCharactersViewModel.favoriteCharacters.observe(
            viewLifecycleOwner,
            { characters ->
                viewAdapter.swapData(characters)
            }
        )
    }

    private fun navigateToDetailFragment(itemView: View) {
        val clickedCharacterPosition = recyclerView.getChildAdapterPosition(itemView)
        val clickedMarvelCharacter =
            marvelCharactersViewModel.favoriteCharacters.value?.get(clickedCharacterPosition)
        clickedMarvelCharacter?.let { marvelCharactersViewModel.setSelected(it) }
        itemView.findNavController().navigate(R.id.show_detail)
    }

    private fun setAdapter(
        dataset: List<MarvelCharacter>,
        onClickListener: View.OnClickListener,
    ) {
        val viewManager = GridLayoutManager(context, 2)
        viewAdapter =
            MarvelCharactersRecyclerViewAdapter(dataset, onClickListener, thumbnailService)
        recyclerView = fragmentFavoritesMasterBinding.recyclerViewFavoriteCharacters
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}