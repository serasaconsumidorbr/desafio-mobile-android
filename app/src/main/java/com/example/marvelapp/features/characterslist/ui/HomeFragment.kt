package com.example.marvelapp.features.characterslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.MainActivity
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentHomeBinding
import com.example.marvelapp.features.characterslist.data.dto.CharacterDetails
import com.example.marvelapp.utils.EMPTY_STRING
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.HomeAdapterListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var charactersLiveData: Flow<PagingData<CharacterDetails>>
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        setupToolBar()
        setSwipeRefreshLayout()
        (requireActivity() as MainActivity).showBottomNavigation()
    }

    private fun setupAdapter() {
        with(binding) {
            homeAdapter = HomeAdapter(this@HomeFragment)
            rcvHeroes.adapter = homeAdapter
            rcvHeroes.layoutManager = LinearLayoutManager(requireContext())
            homeAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.NotLoading -> {
                        showCharactersList()
                    }

                    is LoadState.Loading -> {
                        showLoading()
                    }

                    is LoadState.Error -> {
                        showError()
                    }
                }
                if (loadState.append.endOfPaginationReached && homeAdapter.itemCount == 0) {
                    showEmptyListText()
                }
            }
        }
    }

    private fun showLoading() {
        with(binding) {
            shimmerLayout.isVisible = true
            rcvHeroes.isVisible = false
            imgError.isVisible = false
            txtNotFound.isVisible = false
            txtNotFoundMessage.isVisible = false
            txtError.isVisible = false
        }
    }

    private fun showError() {
        with(binding) {
            shimmerLayout.isVisible = false
            rcvHeroes.isVisible = false
            imgError.isVisible = false
            txtNotFoundMessage.isVisible = false
            txtNotFound.isVisible = false
            imgError.isVisible = true
            txtError.isVisible = true
        }
    }

    private fun showEmptyListText() {
        with(binding) {
            shimmerLayout.isVisible = false
            rcvHeroes.isVisible = false
            imgError.isVisible = true
            txtNotFound.isVisible = true
            txtNotFoundMessage.isVisible = true
            txtError.isVisible = false
        }
    }

    private fun showCharactersList() {
        with(binding) {
            shimmerLayout.isVisible = false
            rcvHeroes.isVisible = true
            imgError.isVisible = false
            txtNotFound.isVisible = false
            txtNotFoundMessage.isVisible = false
            txtError.isVisible = false
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacters(EMPTY_STRING).collect {
                homeAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    private fun setupToolBar() {
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        toolbar?.inflateMenu(R.menu.main_menu)
        toolbar?.menu?.findItem(R.id.search)?.let { menuItem ->
            menuItem.isVisible = true
            setupSearchView(menuItem)
            setupOnCollapseSearchView(menuItem)
        }
    }

    private fun setupSearchView(menuItem: MenuItem) {
        val searchView = menuItem.actionView as SearchView
        val searchEditText: EditText =
            searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText

        searchView.queryHint = getString(R.string.digite_o_nome_do_personagem)
        searchEditText.setTextColor(ContextCompat.getColor(requireContext(), R.color.light))
        searchEditText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.light))

        val clearButton =
            searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)

        clearButton.setOnClickListener {
            searchEditText.text.clear()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    val job = Job()
                    val uiScope = CoroutineScope(Dispatchers.Main + job)
                    uiScope.launch {
                        viewLifecycleOwner.lifecycleScope.launch {
                            charactersLiveData = viewModel.getCharacters(query)
                            charactersLiveData.collect {
                                homeAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            }

                        }
                    }
                }
                resetToolBar()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupOnCollapseSearchView(menuItem: MenuItem) {
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                binding.rcvHeroes.isVisible = false
                (requireActivity() as MainActivity).hideBottomNavigation()
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                (requireActivity() as MainActivity).showBottomNavigation()
                val job = Job()
                val uiScope = CoroutineScope(Dispatchers.Main + job)
                uiScope.launch {
                    viewLifecycleOwner.lifecycleScope.launch {
                        val flow = viewModel.getCharacters(EMPTY_STRING)
                        flow.collect {
                            homeAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                        }
                    }
                }
                return true
            }
        })
    }

    private fun resetToolBar() {
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar?.menu?.clear()
        setupToolBar()
    }

    override fun onItemClick(id: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun setSwipeRefreshLayout() {
        binding.refreshCharacterList.setOnRefreshListener {
            setupObservers()
            binding.refreshCharacterList.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}