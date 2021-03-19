package com.drawiin.myheroes.presentation.ui.heroes_list

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drawiin.myheroes.domain.interactors.GetHeroes
import com.drawiin.myheroes.domain.model.character.Character
import com.drawiin.myheroes.utils.NAMED_API_KEY
import com.drawiin.myheroes.utils.NAMED_HASH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Named

class HeroesViewModel @ViewModelInject constructor(
    private val getHeroes: GetHeroes,
    @Named(NAMED_API_KEY) private val apiKey: String,
    @Named(NAMED_HASH) private val hash: String
) : ViewModel() {
    val heroes get() = _heroes
    private val _heroes by lazy { MutableLiveData<List<Character>>() }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val heroes = getHeroes.execute(apiKey, hash)
                _heroes.postValue(heroes)
            } catch (error: RuntimeException) {
                Log.e("Request Error", error.message.toString())
            }
        }
    }
}