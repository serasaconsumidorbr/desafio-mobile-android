package br.com.marvelcomics.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marvelcomics.base.util.PaginationScrollListener
import br.com.marvelcomics.databinding.ActivityMainBinding
import br.com.marvelcomics.feature.home.adapter.FeatureMarvelCharacterAdapter
import br.com.marvelcomics.feature.home.adapter.MarvelCharacterAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val marvelCharAdapter: MarvelCharacterAdapter by lazy {
        MarvelCharacterAdapter { viewModel.fetchMarvelChars() }
    }

    private val pagerAdapter: FeatureMarvelCharacterAdapter by lazy {
        FeatureMarvelCharacterAdapter()
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            pagerContent.adapter = pagerAdapter

            rvMarvelList.adapter = marvelCharAdapter
            rvMarvelList.addOnScrollListener(object : PaginationScrollListener(layoutManager = rvMarvelList.layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() { viewModel.fetchMarvelChars() }
                override val isLoading: Boolean get() = viewModel.loading.value ?: false
                override val isError: Boolean get() = viewModel.error.value ?: false
            })
        }

        setupObservers()
        viewModel.fetchMarvelChars()
    }

    private fun setupObservers() {
        viewModel.featureCharacters.observe(this) {
            pagerAdapter.items = it
        }

        viewModel.characters.observe(this) {
            marvelCharAdapter.submitData(it)
        }

        viewModel.loading.observe(this) {
            if (viewModel.isInitialFetch()) {
                binding.progress.isVisible = it
            } else {
                marvelCharAdapter.handleLoading(it)
            }
        }

        viewModel.error.observe(this) {
            if (viewModel.isInitialFetch()) {
                binding.errorMessage.isVisible = it
            } else {
                marvelCharAdapter.handleError(it)
            }
        }
    }
}