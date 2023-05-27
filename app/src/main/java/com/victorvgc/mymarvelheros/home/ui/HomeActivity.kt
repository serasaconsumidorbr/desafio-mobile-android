package com.victorvgc.mymarvelheros.home.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.victorvgc.mymarvelheros.R
import com.victorvgc.mymarvelheros.core.ui.BaseActivity
import com.victorvgc.mymarvelheros.core.ui.UIState
import com.victorvgc.mymarvelheros.core.ui.bindImage
import com.victorvgc.mymarvelheros.core.utils.show
import com.victorvgc.mymarvelheros.databinding.ActivityHomeBinding
import com.victorvgc.mymarvelheros.home.domain.domain.Hero
import com.victorvgc.mymarvelheros.home.ui.heroes_list.HeroesAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<List<Hero>>() {

    private val binding: ActivityHomeBinding by binding(R.layout.activity_home)

    override val viewModel: HomeViewModel by viewModel()

    private lateinit var heroesAdapter: HeroesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            lifecycleOwner = this@HomeActivity
        }

        launch {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    is UIState.Initial -> loadingView()
                    is UIState.Loading -> loadingView()
                    is UIState.Success -> displayCarousel(state.data)
                    is UIState.Error -> errorView(state.failure.msg)
                }
            }
        }

        loadHeroesList()
    }

    private fun loadHeroesList() {
        heroesAdapter = HeroesAdapter(viewModel.getHeroesComparator())

        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.adapter = heroesAdapter

        launch {
            viewModel.heroesFlow.collectLatest { heroesPaging ->
                heroesAdapter.submitData(heroesPaging)
            }
        }
    }

    private suspend fun loadingView() {
        withContext(Dispatchers.Main) {
            binding.llScreenOverlay.show()
            binding.progressBar.show()
            binding.ivError.show(false)
            binding.tvError.show(false)
        }
    }

    private suspend fun errorView(msg: String? = null) {
        withContext(Dispatchers.Main) {
            binding.llScreenOverlay.show()
            binding.progressBar.show(false)
            binding.ivError.show()
            binding.tvError.show()

            if (msg != null) {
                binding.tvError.text = msg
            }
        }
    }

    private suspend fun displayCarousel(highlightHeroes: List<Hero>) {
        binding.llScreenOverlay.show(false)

        Log.d("CAROUSEL", "data size: ${highlightHeroes.size}")

        withContext(Dispatchers.Main) {
            binding.carouselView.apply {
                size = highlightHeroes.size
                resource = R.layout.item_carousel_hero
                autoPlay = true
                indicatorAnimationType = IndicatorAnimationType.THIN_WORM
                carouselOffset = OffsetType.CENTER
                setCarouselViewListener { view, position ->
                    val imageView = view.findViewById<ImageView>(R.id.iv_hero)
                    val textView = view.findViewById<TextView>(R.id.tv_hero_name)

                    imageView.bindImage(highlightHeroes[position].imageUrl)
                    textView.text = highlightHeroes[position].name
                }
                show()
            }
        }
    }
}