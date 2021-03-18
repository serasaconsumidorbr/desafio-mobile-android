package com.drawiin.myheroes.presentation.ui.heroes_list

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drawiin.myheroes.useCases.GetHeroes
import com.drawiin.myheroes.utils.NAMED_API_KEY
import com.drawiin.myheroes.utils.NAMED_HASH
import kotlinx.coroutines.launch
import javax.inject.Named

class HeroesViewModel @ViewModelInject constructor(
    private val getHeroes: GetHeroes,
    @Named(NAMED_API_KEY) private val apiKey: String,
    @Named(NAMED_HASH) private val hash: String
) : ViewModel() {
    init {
        viewModelScope.launch {
            try {
                getHeroes.execute(apiKey, hash)
            } catch (error: RuntimeException) {
                Log.e("Request ERROR", error.message.toString())
            }
        }
    }
}