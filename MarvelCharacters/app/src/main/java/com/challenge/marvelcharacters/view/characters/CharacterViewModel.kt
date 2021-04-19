package com.challenge.marvelcharacters.view.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.challenge.marvelcharacters.model.Character
import com.challenge.marvelcharacters.model.Image
import com.challenge.marvelcharacters.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class CharacterViewModel(private val repository : CharacterRepository) : ViewModel(){

    val carouselImageList : MutableLiveData<List<Image>> = MutableLiveData()

    fun loadFirstCharacters(){
        viewModelScope.launch {
            val result  = repository.loadCharacters(5)
            carouselImageList.postValue(result?.map { it.image })
        }
    }

    fun getCarouselImageUrl(pos: Int) : String{
        if(carouselImageList.value.isNullOrEmpty())
            return ""
        val image = carouselImageList.value?.get(pos) ;
        return image?.path +"."+image?.extension
    }

    fun getCharacters() : Flow<PagingData<Character>> {
        return repository.getCharacters().cachedIn(viewModelScope)
    }


}