package com.example.home_domain.di

import com.example.home_domain.usecase.GetHomeCarouselUseCase
import com.example.home_domain.usecase.GetHomeInfinityListUseCase
import com.example.home_domain.usecase.impl.GetHomeCarousel
import com.example.home_domain.usecase.impl.GetHomeInfinityList
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDomainModule {
    @Binds
    @Singleton
    abstract fun bindsGetHomeCarouselUseCase(
        getHomeCarouselUseCaseImpl: GetHomeCarousel,
    ): GetHomeCarouselUseCase

    @Binds
    @Singleton
    abstract fun bindsGetHomeInfinityListUseCase(
        getHomeInfinityList: GetHomeInfinityList,
    ): GetHomeInfinityListUseCase
}