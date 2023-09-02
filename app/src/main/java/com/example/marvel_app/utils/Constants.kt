package com.example.marvel_app.utils

object Constants {

    //Constantes para construção do envio da apikey
    const val QUERY_PARAMETER_TS = "ts"
    const val QUERY_PARAMETER_API_KEY = "apikey"
    const val QUERY_PARAMETER_HASH = "hash"

    //Paging
    const val START_OFFSET = 0
    const val LIMIT = 20

    //Flipper Child Characters State
    const val FLIPPER_CHILD_LOADING = 0
    const val FLIPPER_CHILD_CHARACTERS = 1
    const val FLIPPER_CHILD_ERROR = 2

    //Flipper Child Details State
    const val FLIPPER_CHILD_POSITION_LOADING = 0
    const val FLIPPER_CHILD_POSITION_DETAIL = 1
    const val FLIPPER_CHILD_POSITION_ERROR = 2
    const val FLIPPER_CHILD_POSITION_EMPTY = 3
}