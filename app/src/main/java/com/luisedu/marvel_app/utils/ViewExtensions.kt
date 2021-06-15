package com.luisedu.marvel_app.utils

import android.view.View

fun View.changeVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}