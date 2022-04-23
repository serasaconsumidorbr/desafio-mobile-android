package com.example.marvel.controller.callback

import com.example.marvel.model.Characters

interface Callback{
    fun itemSelected(character: Characters)
    fun onResponseSucessService(response: String)
    fun onResponseErrorService(response: String)
}

