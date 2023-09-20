package com.marvelverse.ui.charactermaster

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import com.marvelverse.R
import com.marvelverse.databinding.FragmentMasterBinding
import com.marvelverse.domain.MarvelCharacter
import com.marvelverse.domain.ThumbnailService
import com.marvelverse.ui.MainActivity
import com.marvelverse.ui.MarvelCharactersViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MasterFragment : Fragment() {

    //We use by activityViewModels instead of by viewModels because we want to share the viewmodel
    //between master and detail fragment associated with the activity lifecycle (using by viewmodel
    // it is associated to the fragment lifecycle)
    private val marvelCharactersViewModel: MarvelCharactersViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MarvelCharactersRecyclerViewAdapter
    private lateinit var fragmentMasterBinding: FragmentMasterBinding
    private lateinit var searchView: SearchView

    @Inject
    lateinit var thumbnailService: ThumbnailService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentMasterBinding = FragmentMasterBinding.inflate(inflater, container, false)
        return fragmentMasterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMasterBinding.marvelCharactersViewModel = marvelCharactersViewModel
        fragmentMasterBinding.lifecycleOwner = this

        val onMarvelCharacterClickListener = View.OnClickListener { itemView ->
            navigateToDetailFragment(itemView)
        }
        setAdapter(listOf(), onMarvelCharacterClickListener)

        val existCharacters = marvelCharactersViewModel.characters.value != null
        if (!existCharacters) {
            marvelCharactersViewModel.loadCharacters()
        }

        fragmentMasterBinding.retryButton.setOnClickListener {
            marvelCharactersViewModel.loadCharacters()
        }

        marvelCharactersViewModel.characters.observe(
            viewLifecycleOwner,
            { characters ->
                viewAdapter.swapData(characters)
            }
        )

        recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            loadMoreCharacterWhenScrollToTheBottom()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(ComponentName(context,
                MainActivity::class.java)))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchText: String): Boolean {
                marvelCharactersViewModel.removeCharacters()
                marvelCharactersViewModel.loadCharacters(searchText)
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                return false
            }
        })
    }

    private fun loadMoreCharacterWhenScrollToTheBottom() {
        if (isTheLastPositionOfTheList() && existsMoreCharactersToLoad()) {
            marvelCharactersViewModel.loadCharacters(getSearchText())
        }
    }

    private fun isTheLastPositionOfTheList(): Boolean {
        val layoutManager: GridLayoutManager =
            fragmentMasterBinding.recyclerViewCharacters.layoutManager as GridLayoutManager
        val lastCharacterPosition = marvelCharactersViewModel.characters.value!!.size - 1
        return lastCharacterPosition != 0 && layoutManager.findLastCompletelyVisibleItemPosition() == lastCharacterPosition
    }

    private fun existsMoreCharactersToLoad(): Boolean {
        val lastCharacterPosition = marvelCharactersViewModel.characters.value!!.size - 1
        return lastCharacterPosition < marvelCharactersViewModel.totalCharacters.value!!

    }

    private fun getSearchText(): String? {
        val searchText: String = searchView.query.toString()
        if (searchText.isEmpty()) {
            return null
        }
        return searchText
    }

    private fun navigateToDetailFragment(itemView: View) {
        val clickedCharacterPosition = recyclerView.getChildAdapterPosition(itemView)
        val clickedMarvelCharacter =
            marvelCharactersViewModel.characters.value?.get(clickedCharacterPosition)
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
        recyclerView = fragmentMasterBinding.recyclerViewCharacters
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}