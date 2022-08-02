package com.developer.marvel.utils

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

object MockServer {
    private var server: MockWebServer? = null

    fun start(statusCode: Int, body: String): HttpUrl {
        server = MockWebServer()
        server!!.start()
        server!!.enqueue(MockResponse().setResponseCode(statusCode).setBody(body))

        return server!!.url("")
    }

    fun stop() {
        server?.shutdown()
    }

}