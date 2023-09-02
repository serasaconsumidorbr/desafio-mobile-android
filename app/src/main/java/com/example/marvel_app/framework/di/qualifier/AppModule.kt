package com.example.marvel_app.framework.di.qualifier

import com.example.marvel_app.framework.imageloader.GlideImageLoader
import com.example.marvel_app.framework.imageloader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface AppModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
}