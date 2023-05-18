package br.com.marvelcomics.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.marvelcomics.R
import br.com.marvelcomics.feature.adapter.MarvelCharacterLoadAdapter
import br.com.marvelcomics.feature.adapter.MarvelCharacterPageAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private val marvelCharAdapter: MarvelCharacterPageAdapter by lazy {
        MarvelCharacterPageAdapter {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rv_marvel_list)

        rv.adapter = marvelCharAdapter.withLoadStateHeaderAndFooter(
            header = MarvelCharacterLoadAdapter { marvelCharAdapter.retry() },
            footer = MarvelCharacterLoadAdapter { marvelCharAdapter.retry() }
        )


        lifecycleScope.launchWhenCreated {
            viewModel.characters.collectLatest {
                marvelCharAdapter.submitData(it)
            }
        }
    }
}