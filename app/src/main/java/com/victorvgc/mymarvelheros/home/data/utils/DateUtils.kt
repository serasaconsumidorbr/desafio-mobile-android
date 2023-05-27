package com.victorvgc.mymarvelheros.home.data.utils

import java.util.Date

class DateUtils {
    fun getCurrentTimestamp(): String {
        val date = Date().time

        return "$date"
    }
}