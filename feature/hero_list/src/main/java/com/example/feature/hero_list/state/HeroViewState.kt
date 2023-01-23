package com.example.feature.hero_list.state

import com.example.domain.heroes.model.Page

sealed class HeroViewState {
    object Loading: HeroViewState()
   data class Failure(val e: Throwable): HeroViewState()
   data class Success(val page: Page): HeroViewState()
}