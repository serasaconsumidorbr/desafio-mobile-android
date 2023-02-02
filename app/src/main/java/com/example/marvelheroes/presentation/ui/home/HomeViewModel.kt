package com.example.marvelheroes.presentation.ui.home

import com.example.marvelheroes.domain.services.HeroesService
import com.example.marvelheroes.presentation.extensions.reportErrorWarning
import com.example.marvelheroes.presentation.ui.baseViewModel.BaseViewModel
import com.example.marvelheroes.presentation.ui.baseViewModel.Event
import kotlinx.coroutines.CoroutineScope

class HomeViewModel(
    private val heroesService: HeroesService
) : BaseViewModel(), CoroutineScope {

    fun getHeroesMarvel() {
        exec(param = Unit,
            service = heroesService,
            shouldShowErrorMessage = false,
            onSuccessResult = {

            },
            onError = { _, error, _ ->
                error?.let {
                    message.value = Event(error)
                    reportErrorWarning()
                }
                error
            })
    }
}