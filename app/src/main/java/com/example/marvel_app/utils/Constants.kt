package com.example.marvel_app.utils

object Constants {

    //Constantes para construção do envio da apikey
    const val QUERY_PARAMETER_TS = "ts"
    const val QUERY_PARAMETER_API_KEY = "apikey"
    const val QUERY_PARAMETER_HASH = "hash"

    //Paging
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

    //Flipper Favorite State
    const val FLIPPER_FAVORITE_CHILD_POSITION_SUCCESS = 0
    const val FLIPPER_FAVORITE_CHILD_POSITION_LOADING = 1

    //Database Constants
    const val APP_DATABASE_NAME = "app_database"
    const val FAVORITES_TABLE_NAME = "favorites"
    const val FAVORITES_COLUMN_INFO_ID = "id"
    const val FAVORITES_COLUMN_INFO_NAME = "name"
    const val FAVORITES_COLUMN_INFO_IMAGE_URL = "image_url"
}