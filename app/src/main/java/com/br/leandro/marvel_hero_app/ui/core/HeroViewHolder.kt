package com.br.leandro.marvel_hero_app.ui.core

import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.br.leandro.marvel_hero_app.R
import com.br.leandro.marvel_hero_app.databinding.HeroItemListBinding
import com.br.leandro.marvel_hero_app.domain.hero.Hero
import com.br.leandro.marvel_hero_app.domain.hero.imageUrl
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class HeroViewHolder(
    private val binding: HeroItemListBinding,
    private val requestManager: RequestManager
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        hero: Hero,
        onFavoriteButtonClick: OnFavoriteButtonClick? = null,
        isHeroFavorite: Boolean = true
    ) {
        val missingDataString = binding.root.context.getString(R.string.hero_missing_data)
        binding.homeHeroName.text = hero.name
        binding.homeHeroDescription.text =
            if (hero.description.isNotEmpty()) hero.description else missingDataString
        binding.homeHeroFavoriteCheckBox.isChecked = isHeroFavorite
        requestManager
            .load(hero.imageUrl(ThumbnailOrientation.PORTRAIT, ThumbnailSize.MEDIUM))
            .placeholder(R.drawable.ic_na)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.homeHeroThumbnail)
            .clearOnDetach()

        onFavoriteButtonClick?.let { listener ->
            binding.homeHeroFavoriteCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    listener.onFavoriteClicked(hero, true)
                else
                    listener.onFavoriteClicked(hero, false)

            }
        }

        binding.root.setOnClickListener {
            val bundle = bundleOf(BundleKey.HERO_DETAIL.key to hero)
            val action = if (isHeroFavorite) R.id.action_navigation_favorites_to_navigation_hero
            else R.id.action_navigation_home_to_navigation_hero
            Navigation.findNavController(binding.root).navigate(action, bundle)
        }
    }
}

interface OnFavoriteButtonClick {
    fun onFavoriteClicked(hero: Hero, shouldSave: Boolean)
}