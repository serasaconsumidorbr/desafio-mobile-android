package com.cajusoftware.marvelcharacters.data.network

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No internet connection"
}