package com.luisedu.marvel_app.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luisedu.marvel_app.data.repository.MarvelApiRepository
import com.luisedu.marvel_app.viewmodel.HomeViewModel

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HomeViewModel(
        MarvelApiRepository()
    ) as T
}