package com.br.leandro.marvel_hero_app.common.di.ui

import androidx.lifecycle.ViewModel
import com.br.leandro.marvel_hero_app.ui.favorite.FavoriteFragment
import com.br.leandro.marvel_hero_app.ui.hero.HeroFragment
import com.br.leandro.marvel_hero_app.ui.hero.HeroViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class FragmentBuilderModule {

    //region HomeFragment
    @ContributesAndroidInjector(modules = [ViewModelProviderFactory::class])
    abstract fun bindsHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    //endregion

    //region Hero Fragment
    @ContributesAndroidInjector(modules = [ViewModelProviderFactory::class])
    abstract fun bindsHeroFragment(): HeroFragment

    @Binds
    @IntoMap
    @ViewModelKey(HeroViewModel::class)
    abstract fun bindHeroViewModel(viewModel: HeroViewModel): ViewModel
    // endregion

    //region Favorite Fragment
    @ContributesAndroidInjector(modules = [ViewModelProviderFactory::class])
    abstract fun bindsFavoriteFragment(): FavoriteFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
    //endregion
    //alterado
}