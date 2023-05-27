package com.victorvgc.mymarvelheros.core.utils

import android.os.SystemClock
import android.view.View

fun View.show(show: Boolean = true, invisible: Boolean = false) {
    val visibilityState = if (invisible) View.INVISIBLE else View.GONE
    if (invisible.not())
        this.visibility = if (show) View.VISIBLE else visibilityState
    else
        this.visibility = visibilityState
}

fun View.hide(invisible: Boolean = false) {
    if (invisible) {
        this.visibility = View.INVISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.setOnceClickListener(debounceTime: Long = 600L, action: (view: View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}