package com.example.home_data.di

import com.example.home_data.remote.HomeApi
import com.example.home_data.remote.configs.CarouselConfig
import com.example.home_data.remote.configs.HomePageConfig
import com.example.home_data.remote.datasource.HomeListDataSource
import com.example.home_data.remote.datasource.HomeListDataSourceImpl
import com.example.home_data.remote.datasource.offset.OffsetCalculator
import com.example.home_data.remote.datasource.offset.OffsetCalculatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object HomeDataModule {

    @Provides
    fun providesHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)

    @Provides
    fun providesCarouselComponentConfig(): CarouselConfig = CarouselConfig(
        startIndex = 0,
        quantity = 5
    )

    @Provides
    fun providesHomePageConfig(carouselConfig: CarouselConfig): HomePageConfig = HomePageConfig(
        size = 20,
        startingIndex = carouselConfig.quantity,
        incrementValue = 1
    )
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeDataModuleBinder {

    @Binds
    abstract fun bindsHomeListDataSource(homeListDataSourceImpl: HomeListDataSourceImpl): HomeListDataSource

    @Binds
    abstract fun bindsOffsetCalculator(offsetCalculatorImpl: OffsetCalculatorImpl): OffsetCalculator
}
