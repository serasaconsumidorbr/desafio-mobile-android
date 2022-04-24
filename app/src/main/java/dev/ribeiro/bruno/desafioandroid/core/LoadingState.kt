package dev.ribeiro.bruno.desafioandroid.core

sealed class LoadingState {

    object ShowLoading: LoadingState()

    object HideLoading: LoadingState()

}