package com.ncz.desafio_mobile_android.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncz.desafio_mobile_android.domain.entities.character.Character
import com.ncz.desafio_mobile_android.domain.repositories.InterfaceCharacterRepository
import com.ncz.desafio_mobile_android.domain.usescases.CharacterUseCases
import com.ncz.desafio_mobile_android.domain.utils.State
import com.ncz.desafio_mobile_android.external.datasource.CharacterDataSource
import com.ncz.desafio_mobile_android.infrastructure.datasources.InterfaceCharacterDataSource
import com.ncz.desafio_mobile_android.infrastructure.repository.CharacterRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val dataSource: InterfaceCharacterDataSource = CharacterDataSource()

    private val repository: InterfaceCharacterRepository = CharacterRepository(dataSource)

    val useCases = CharacterUseCases(repository)

    val liveData: MutableLiveData<State<List<Character>>> by lazy { MutableLiveData() }

    fun getCharacter() {
        viewModelScope.launch {
            try {
                val release = useCases.getCharacter()
                liveData.postValue(State.success(release))
            } catch (e: IOException) {
                liveData.postValue(State.networkError())
            } catch (e: Exception) {
                liveData.postValue(State.error())
            }
        }
    }
}