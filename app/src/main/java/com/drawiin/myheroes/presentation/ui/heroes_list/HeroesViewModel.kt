package com.drawiin.myheroes.presentation.ui.heroes_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.drawiin.myheroes.useCases.GetHeroes
import com.drawiin.myheroes.utils.NAMED_API_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Named

@AndroidEntryPoint
class HeroesViewModel @ViewModelInject constructor(
    private val getHeroes: GetHeroes,
    @Named(NAMED_API_KEY) private val apiKey: String
) : ViewModel()