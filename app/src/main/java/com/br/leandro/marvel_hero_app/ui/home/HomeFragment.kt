package com.br.leandro.marvel_hero_app.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.leandro.marvel_hero_app.R
import com.br.leandro.marvel_hero_app.databinding.FragmentHomeBinding
import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.ui.core.BaseFragment
import com.br.leandro.marvel_hero_app.ui.core.InfiniteRecyclerViewScrollListener
import com.br.leandro.marvel_hero_app.ui.core.OnFavoriteButtonClick
import com.br.leandro.marvel_hero_app.ui.home.list.HomeHeroListAdapter


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), OnFavoriteButtonClick {

    override val mViewModel: HomeViewModel by navGraphViewModels(R.id.mobile_navigation) { mViewModelFactory }

    private val homeHeroListAdapter: HomeHeroListAdapter by lazy {
        HomeHeroListAdapter(onFavoriteButtonClick = this, requestManager = requestManagerGlide)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }
    override fun setupViewModel() {
        mViewModel.heroList.observe(viewLifecycleOwner, Observer { heroList ->
            heroList.forEachIndexed { index, hero ->
                Log.d("Hero", "$index -> $hero.id")
            }
            if (heroList.isNotEmpty()) {
                homeHeroListAdapter.submitList(heroList)
                changeToHeroesLayout(mViewBinding.viewSwitcherHomeScreen.displayedChild)
            } else {
                mViewBinding.viewSwitcherHomeScreen.displayedChild = VILLAIN_LAYOUT_INTERNET_PROBLEM
            }
        })
        if (mViewModel.heroList.value.isNullOrEmpty())
            mViewModel.getListOfHeroes()
    }

    override fun setupView() {
        mViewBinding.homeHeroRecyclerView.apply {
            layoutManager = LinearLayoutManager(fragmentContext)
            adapter = homeHeroListAdapter
            addOnScrollListener(object :
                InfiniteRecyclerViewScrollListener(this.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    mViewModel.getListOfHeroes(Companion.OFF_SET_TO_LOAD_MORE_HEROES_PER_PAGE * page)
                }
            })
        }

        mViewBinding.fabRetryLoadingHeroes.setOnClickListener {
            mViewModel.getListOfHeroes()
            mViewBinding.searchHero.setText("")
        }

        mViewBinding.searchHero.apply {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}

                override fun onTextChanged(s: CharSequence?, st: Int, bf: Int, ct: Int) {
                    mViewModel.filterHeroByName(s.toString())
                }
            })
            setText(mViewModel.latestSearch)
        }
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onFavoriteClicked(hero: Hero, shouldSave: Boolean) {
        if (shouldSave)
            mViewModel.saveFavoriteHero(hero)
        else
            mViewModel.deleteFavoriteHero(hero)
    }

    private fun changeToHeroesLayout(childDisplayed: Int) {
        if (childDisplayed == VILLAIN_LAYOUT_INTERNET_PROBLEM) {
            mViewBinding.viewSwitcherHomeScreen.displayedChild = HERO_LAYOUT_INTERNET_SUCCESS
        }
    }

    companion object {
        private const val OFF_SET_TO_LOAD_MORE_HEROES_PER_PAGE = 5
        private const val VILLAIN_LAYOUT_INTERNET_PROBLEM = 0
        private const val HERO_LAYOUT_INTERNET_SUCCESS = 1
    }
}
