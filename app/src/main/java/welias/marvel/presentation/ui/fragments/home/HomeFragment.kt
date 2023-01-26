package welias.marvel.presentation.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import welias.marvel.R
import welias.marvel.core.exception.ErrorException
import welias.marvel.core.extensions.PaginationScrollListener
import welias.marvel.databinding.FragmentHomeBinding
import welias.marvel.presentation.model.CharacterUI
import welias.marvel.presentation.ui.fragments.home.adapters.CharacterAdapter
import welias.marvel.presentation.ui.fragments.home.adapters.TopCharactersAdapter

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var adapter: CharacterAdapter
    private lateinit var topAdapter: TopCharactersAdapter
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMainAdapter()
        setupTopAdapter()
        setupGetCharacters()
        setupObserver()
        setupErrorListener()
    }

    // Region Setup
    private fun setupMainAdapter() {
        addScrollListener()
        adapter = CharacterAdapter()
        viewBinding.characters.adapter = adapter
    }

    private fun setupTopAdapter() {
        topAdapter = TopCharactersAdapter()
        viewBinding.topRecyclerView.adapter = topAdapter
    }

    private fun setupGetCharacters() {
        viewModel.getListCharacters()
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeState.collect { uiState ->
                showState(uiState)
            }
        }
    }

    private fun setupErrorListener() {
        viewBinding.errorNoConnection.tryAgain {
            setupGetCharacters()
        }

        viewBinding.retry.setOnClickListener {
            setupGetCharacters()
        }
    }

    private fun addScrollListener() {
        viewBinding.characters.addOnScrollListener(object :
                PaginationScrollListener(viewBinding.characters.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.getListCharacters()
                }

                override fun isLastPage(): Boolean {
                    return viewModel.homeState.value.characters?.size == viewModel.homeState.value.dataApi.totalItems
                }

                override fun isLoading(): Boolean {
                    return viewModel.homeState.value.isLoading
                }
            })
    }

    private fun updateList(homeState: HomeState) {
        homeState.characters?.let { characters -> submitList(characters) }
        homeState.listTopCharacters?.let { topCharacters -> submitTopList(topCharacters) }
    }

    private fun submitList(characters: List<CharacterUI>) {
        adapter.submitList(characters)
        adapter.updateLoading(characters.size)
    }

    private fun submitTopList(characters: List<CharacterUI>) {
        topAdapter.submitList(characters)
    }

    // Region State
    private fun showState(homeState: HomeState) {
        if (homeState.isLoading) {
            if (homeState.isFirstRequisition) {
                showLoadingState()
            } else {
                showCharactersState(homeState)
            }
        } else if (homeState.error != null) {
            showErrorState(homeState)
        } else {
            showCharactersState(homeState)
        }
    }

    private fun showLoadingState() {
        setupLoadingVisibility(true)
        setupCharactersVisibility(false)
        setupErrorNoConnectionVisibility(false)
    }

    private fun showCharactersState(homeState: HomeState) {
        val showCharacters = !homeState.characters.isNullOrEmpty()
        setupLoadingVisibility(!showCharacters)
        setupCharactersVisibility(showCharacters)
        setupErrorNoConnectionVisibility(!showCharacters)
        updateList(homeState = homeState)
    }

    private fun showErrorState(homeState: HomeState) {
        val showError = homeState.error != null
        val charactersIsNullOrEmpty = homeState.characters.isNullOrEmpty()

        setupLoadingVisibility(!showError)
        setupCharactersVisibility(!showError)

        showError(homeState = homeState, charactersIsNullOrEmpty = charactersIsNullOrEmpty)
    }

    // Region Error
    private fun showError(homeState: HomeState, charactersIsNullOrEmpty: Boolean) {
        with(homeState) {
            if (error != null) {
                when (error) {
                    is ErrorException.NoConnectionError -> showServerError(charactersIsNullOrEmpty)
                    is ErrorException.ServerError -> showServerError(charactersIsNullOrEmpty)
                }
            }
        }
    }

    private fun showServerError(charactersIsNullOrEmpty: Boolean) {
        showToast()

        if (!charactersIsNullOrEmpty) {
            setupCharactersVisibility(true)
        } else {
            setupErrorNoConnectionVisibility(true)
        }
    }

    private fun showToast() {
        Toast.makeText(context, getString(R.string.no_connection_error), Toast.LENGTH_LONG).show()
    }

    // Region Visibility
    private fun setupLoadingVisibility(value: Boolean) {
        viewBinding.loading.isVisible = value
    }

    private fun setupErrorNoConnectionVisibility(value: Boolean) {
        viewBinding.errorNoConnection.setupVisibility(value)
    }

    private fun setupCharactersVisibility(value: Boolean) {
        viewBinding.characters.isVisible = value
        viewBinding.topRecyclerView.isVisible = value
        viewBinding.allHeroes.isVisible = value
        viewBinding.mainCharacters.isVisible = value
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
