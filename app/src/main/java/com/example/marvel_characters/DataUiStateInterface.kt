package com.example.marvel_characters

abstract  class BaseDataUiState(
    open val loading: Boolean = false,
    open val error: String? = null
) {
    fun hadAnError()= error!=null
}