package com.challenge.marvelcharacters.di

import com.challenge.marvelcharacters.view.characters.CharacterViewModel
import com.challenge.marvelcharacters.repository.CharacterRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
   single { CharacterRepository() }
   viewModel { CharacterViewModel(get()) }
}