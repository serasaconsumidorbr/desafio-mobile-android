package com.example.marvel.services.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.marvel.controller.callback.Callback
import com.example.marvel.model.Characters
import com.example.marvel.services.model.Marvel_v1_Characters
import com.google.gson.Gson
import org.json.JSONObject

class MarvelApi(application: Application) {

    private val queue = Volley.newRequestQueue(application)

    fun getCharacters(context: Context) {

        val url = BASE_URL + CHARACTERS + API_KEY + API_TIMESPAM + API_HASH

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response -> (context as Callback).onResponseSucessService(response.toString()) },
            Response.ErrorListener{ error ->
                Log.e(this.toString(), error.message ?: "Error")
                (context as Callback).onResponseErrorService(error.message ?: "Error")
            }
        )

        queue.add(request)

    }

    companion object {

        const val API_KEY = "?apikey=1f5cf484796013b9abc14a80e02f9fb5"
        const val API_TIMESPAM = "&ts=1650396592"
        const val API_HASH = "&hash=a34974cb3f204c721924461f54d5af2a"

        const val BASE_URL = "https://gateway.marvel.com:443/v1/"
        const val CHARACTERS = "/public/characters"

    }


}