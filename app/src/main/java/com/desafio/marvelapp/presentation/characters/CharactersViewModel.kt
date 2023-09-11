package com.desafio.marvelapp.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import com.project.core.domain.model.Character
import com.project.core.usecase.IGetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val LIMIT = 5
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: IGetCharactersUseCase
): ViewModel() {

    private val _character = MutableLiveData<List<Character>>()
    val character: LiveData<List<Character>> = _character

    init {
        charactersCarousel()
    }

    fun charactersPagingData(query: String):Flow<PagingData<Character>>{
        return charactersUseCase(
            IGetCharactersUseCase.GetCharactersParams(
                query = query,
                pagingConfig = getPageConfig()
            )
        ).cachedIn(viewModelScope)
    }

    private fun getPageConfig() = PagingConfig(
        pageSize = 20
    )

    private fun charactersCarousel() {
        viewModelScope.launch {
            charactersUseCase(getCarouselConfig()).collect {
                _character.postValue(it)
            }
        }
    }

    private fun getCarouselConfig() = LIMIT
}