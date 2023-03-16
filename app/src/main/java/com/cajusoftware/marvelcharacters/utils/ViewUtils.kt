package com.cajusoftware.marvelcharacters.utils

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.cajusoftware.marvelcharacters.ui.MainActivity

fun View.visible() {
    visibility = VISIBLE
}

fun View.gone() {
    visibility = GONE
}

fun AppCompatActivity.setStatusBarColorForUnderApi23() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        window.statusBarColor = Color.BLACK
    }
}

fun FragmentActivity.hideToolbar() {
    (this as? MainActivity)?.actionBar?.hide()
}