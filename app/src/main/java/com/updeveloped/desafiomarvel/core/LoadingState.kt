package com.updeveloped.desafiomarvel.core

sealed class LoadingState {
    object ShowLoading: LoadingState()
    object HideLoading: LoadingState()
}