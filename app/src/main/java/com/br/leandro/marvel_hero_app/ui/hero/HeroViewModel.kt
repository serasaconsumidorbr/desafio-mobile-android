package com.br.leandro.marvel_hero_app.ui.hero

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class HeroViewModel @Inject constructor(
    private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase
) : ViewModel() {

    private val _hero = MutableLiveData<Hero?>()

    val hero: LiveData<Hero?>
        get() = _hero

    private val _favoriteButtonResult = MutableLiveData<Boolean>()

    val favoriteButtonResult: LiveData<Boolean>
        get() = _favoriteButtonResult

    fun displayHero(hero: Hero?) {
        _hero.postValue(hero)
    }

    private fun handleSaveFavoriteHero(result: ActionResult<Boolean>) {
        when (result) {
            is Success -> {
                Log.d("handle saving hero", result.data.toString())
                _favoriteButtonResult.value = true
            }
            is Failure -> {
                Log.d("handle saving hero failing", result.failure.message.orEmpty())
                _favoriteButtonResult.value = false
            }
            is Loading -> {
                _favoriteButtonResult.value = false
            }
        }
    }

    fun saveFavoriteHero(hero: Hero) {
        viewModelScope.launch {
            saveHeroInDataBaseUseCase
                .execute(hero)
                .collect { it ->
                    handleSaveFavoriteHero(it)
                }
        }
    }
}