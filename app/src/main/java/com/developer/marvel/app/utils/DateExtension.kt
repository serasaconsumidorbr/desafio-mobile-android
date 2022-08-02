package com.developer.marvel.app.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.toDateFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    return simpleDateFormat.format(this)
}