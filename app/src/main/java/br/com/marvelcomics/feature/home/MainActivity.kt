package br.com.marvelcomics.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marvelcomics.R
import br.com.marvelcomics.base.extensions.asMarvelCharacterEntry
import br.com.marvelcomics.base.extensions.asMarvelCharacterEntryWithFeatures
import br.com.marvelcomics.base.util.PaginationScrollListener
import br.com.marvelcomics.base.util.UiException
import br.com.marvelcomics.databinding.ActivityMainBinding
import br.com.marvelcomics.feature.home.adapter.MarvelCharacterAdapter
import br.com.marvelcomics.model.MarvelCharacterEntry
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val marvelCharAdapter: MarvelCharacterAdapter by lazy {
        MarvelCharacterAdapter { viewModel.fetchMarvelChars() }
    }

    companion object {
        private const val MINIMUM_CHAR_FEATURE_SIZE_LIST = 5
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            rvMarvelList.adapter = marvelCharAdapter
            rvMarvelList.addOnScrollListener(object :
                PaginationScrollListener(layoutManager = rvMarvelList.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.fetchMarvelChars()
                }

                override val isLoading: Boolean get() = viewModel.loading.value ?: false
                override val isError: Boolean get() = viewModel.error.value?.first ?: false
            })

            btnTryAgain.setOnClickListener {
                viewModel.fetchMarvelChars()
            }
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.characters.observe(this) { characters ->
            if (marvelCharAdapter.isInitialData() && characters.size > MINIMUM_CHAR_FEATURE_SIZE_LIST) {
                marvelCharAdapter.submitDataWithFeatures(
                    characters.asMarvelCharacterEntryWithFeatures(
                        featureTitle = MarvelCharacterEntry.Title(R.string.feature),
                        characterTitle = MarvelCharacterEntry.Title(R.string.more),
                    )
                )
            } else {
                marvelCharAdapter.submitData(characters.asMarvelCharacterEntry())
            }

        }

        viewModel.loading.observe(this) { loading ->
            if (!viewModel.isInitialFetch()) {
                marvelCharAdapter.handleLoading(loading)
            }
            binding.progress.isVisible = loading && viewModel.isInitialFetch()
        }

        viewModel.error.observe(this) { error ->
            if (!viewModel.isInitialFetch()) {
                marvelCharAdapter.handleError(error.first, getErrorMessage(error.second))
            }

            binding.errorMessage.isVisible = error.first && viewModel.isInitialFetch()
            binding.btnTryAgain.isVisible = error.first && viewModel.isInitialFetch()

            if (error.first) {
                binding.errorMessage.text = getErrorMessage(error.second)
            }
        }
    }

    private fun getErrorMessage(uiException: UiException?): String {
        val message = when (uiException) {
            is UiException.GenericApiException -> R.string.generic_api_message
            is UiException.TimeoutException -> R.string.timeout_message
            else -> R.string.generic_error_message
        }
        return getString(message)
    }
}