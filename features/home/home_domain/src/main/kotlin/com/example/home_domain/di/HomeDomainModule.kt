package com.example.home_domain.di

import com.example.home_domain.mapper.ResultHomeCarouselToUiStateMapper
import com.example.home_domain.mapper.impl.ResultHomeCarouselToUiState
import com.example.home_domain.usecase.GetHomeCarouselUseCase
import com.example.home_domain.usecase.GetHomeInfinityListUseCase
import com.example.home_domain.usecase.impl.GetHomeCarousel
import com.example.home_domain.usecase.impl.GetHomeInfinityList
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeDomainModule {
    @Binds
    abstract fun bindsGetHomeCarouselUseCase(
        getHomeCarouselUseCaseImpl: GetHomeCarousel,
    ): GetHomeCarouselUseCase

    @Binds
    abstract fun bindsGetHomeInfinityListUseCase(
        getHomeInfinityList: GetHomeInfinityList,
    ): GetHomeInfinityListUseCase
}

@Module
@InstallIn(ViewModelComponent::class)
object HomeDomainModuleProvider {
    @Provides
    fun providesResultHomeCarouselToUiStateMapper(): ResultHomeCarouselToUiStateMapper =
        ResultHomeCarouselToUiState()
}
