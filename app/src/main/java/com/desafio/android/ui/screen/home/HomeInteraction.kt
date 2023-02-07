package com.desafio.android.ui.screen.home

sealed class HomeInteraction {
    object ResetCharacters : HomeInteraction()
    object GetCharacters : HomeInteraction()
    data class SetIsLastItemVisible(val isVisible: Boolean): HomeInteraction()

}