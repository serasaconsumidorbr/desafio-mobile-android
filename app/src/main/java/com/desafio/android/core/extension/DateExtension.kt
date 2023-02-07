package com.desafio.android.core.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toTimestamp(pattern: String = "dd/M/yyyy hh:mm:ss"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}