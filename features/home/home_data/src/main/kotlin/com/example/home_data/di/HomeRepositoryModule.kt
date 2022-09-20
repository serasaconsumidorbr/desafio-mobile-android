package com.example.home_data.di

import com.example.home_data.remote.HomeApi
import com.example.home_data.remote.configs.CarouselConfig
import com.example.home_data.remote.configs.HomePageConfig
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_data.remote.mapper.CharactersDataDtoToCharactersMapper
import com.example.home_data.remote.repository.HomeCarouselRepositoryImpl
import com.example.home_data.remote.repository.HomeListRepositoryImpl
import com.example.home_domain.repository.HomeCarouselRepository
import com.example.home_domain.repository.HomeListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object HomeRepositoryModule {

    @Provides
    fun providesHomeListRepository(
        homeListDataSource: HomeListDataSource,
        homePageConfig: HomePageConfig,
    ): HomeListRepository = HomeListRepositoryImpl(
        homeListDataSource = homeListDataSource,
        pageSize = homePageConfig.size
    )

    @Provides
    fun providesHomeCarouselRepository(
        api: HomeApi,
        charactersDataDtoToCharacters: CharactersDataDtoToCharactersMapper,
        carouselConfig: CarouselConfig,
    ): HomeCarouselRepository = HomeCarouselRepositoryImpl(
        api = api,
        charactersDataDtoToCharacters = charactersDataDtoToCharacters,
        carouselConfig = carouselConfig
    )

}
