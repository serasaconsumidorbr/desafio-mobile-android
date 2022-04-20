package com.br.leandro.marvel_hero_app.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.br.leandro.marvel_hero_app.R
import com.br.leandro.marvel_hero_app.databinding.FragmentHeroBinding
import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.ui.core.BaseFragment
import com.br.leandro.marvel_hero_app.ui.core.BundleKey
import com.br.leandro.marvel_hero_app.ui.core.ThumbnailOrientation.LANDSCAPE
import com.br.leandro.marvel_hero_app.ui.core.ThumbnailSize.LARGE
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar


class HeroFragment : BaseFragment<HeroViewModel, FragmentHeroBinding>() {

    override val mViewModel: HeroViewModel by navGraphViewModels(R.id.mobile_navigation) { mViewModelFactory }

    private var hero: Hero? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hero = arguments?.getParcelable<Hero>(BundleKey.HERO_DETAIL.key)
        return mViewBinding.root
    }

    override fun getViewBinding(): FragmentHeroBinding {
        return FragmentHeroBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        mViewModel.hero.observe(viewLifecycleOwner, Observer { observableHero ->
            observableHero?.let {
                mViewBinding.heroName.text = observableHero.name
                mViewBinding.heroDescription.text = observableHero.description
                mViewBinding.heroImage.alpha = 1.0f
                mViewBinding.heroContainer.apply {
                    alpha = 1.0f
                    background = null
                }
                requestManagerGlide
                    .load(observableHero.imageUrl(LANDSCAPE, LARGE))
                    .placeholder(R.drawable.ic_na)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mViewBinding.heroImage)
                    .clearOnDetach()
                mViewBinding.heroFavoriteButton.apply {
                    show()
                    setOnClickListener {
                        mViewModel.saveFavoriteHero(observableHero)
                    }
                }
            }
        })

        mViewModel.favoriteButtonResult.observe(
            viewLifecycleOwner, Observer { favoriteButton ->
                if (favoriteButton) {
                    val make = Snackbar.make(
                        mViewBinding.root,
                        getString(R.string.detail_save_favorite_hero_button),
                        Snackbar.LENGTH_SHORT
                    )
                    make.animationMode = Snackbar.ANIMATION_MODE_FADE
                    make.show()
                }
            })
    }

    override fun setupView() {
        hero?.let {
            mViewModel.displayHero(it)
            return
        }
    }
}
