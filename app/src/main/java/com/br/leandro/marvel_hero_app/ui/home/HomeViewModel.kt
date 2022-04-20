package com.br.leandro.marvel_hero_app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHeroesListUseCase: GetHeroesListUseCase,
    private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase,
    private val deleteHeroInDataBaseUseCase: DeleteHeroInDataBaseUseCase
) : ViewModel() {

    private val heroCache = mutableSetOf<Hero>()
    private val filteredHeroCache = mutableSetOf<Hero>()
    private val _heroList = MutableLiveData<List<Hero>>()

    val heroList: LiveData<List<Hero>>
        get() = _heroList

    var latestSearch: String = ""


    private fun handleListOfHeroes(result: ActionResult<List<Hero>>) {
        when (result) {
            is Success -> {
                heroCache.addAll(result.data)
                _heroList.postValue(heroCache.toList())
            }
            is Failure -> {
                heroCache.clear()
                _heroList.postValue(heroCache.toList())
            }
        }
    }

    private fun handleSaveFavoriteHero(result: ActionResult<Boolean>) {
        when (result) {
            is Success -> {
                Log.d("handle saving hero", result.data.toString())
            }
            is Failure -> {
                Log.d("handle saving hero failing", result.failure.message.orEmpty())
            }
            is Loading -> {
                Log.d("handle saving hero loading", result.isLoading.toString())
            }
        }
    }

    private fun handleDeleteFavoriteHero(result: ActionResult<Boolean>) {
        when (result) {
            is Success -> {
                Log.d("handle delete hero", result.data.toString())
            }
            is Failure -> {
                Log.d("handle delete hero failure", result.failure.message.orEmpty())
            }
            is Loading -> {
                Log.d("handle delete hero loading", result.isLoading.toString())
            }
        }
    }


    fun getListOfHeroes(numberOfHeroes: Int = 0) {
        viewModelScope.launch {
            getHeroesListUseCase
                .execute(numberOfHeroes)
                .collect { it -> handleListOfHeroes(it) }
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

    fun deleteFavoriteHero(hero: Hero) {
        viewModelScope.launch {
            deleteHeroInDataBaseUseCase
                .execute(hero)
                .collect {
                    handleDeleteFavoriteHero(it)
                }
        }
    }

    fun filterHeroByName(heroName: String) {
        val filteredHeroes = heroCache.filter { hero ->
            hero.name.contains(Regex(heroName, RegexOption.IGNORE_CASE))
        }
        if (filteredHeroes.isEmpty()) {
            _heroList.postValue(heroCache.toList())
        } else {
            _heroList.postValue(filteredHeroes)
        }
        latestSearch = heroName
    }
}