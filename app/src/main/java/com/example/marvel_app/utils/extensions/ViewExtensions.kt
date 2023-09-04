package com.example.marvel_app.utils.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

private const val SNACKBAR_MAX_LINES = 5

fun View.showSnackBar(
    @StringRes messageId: Int,
    backgroundColor: Int) {
    val snackBar = Snackbar.make(
        this,
        resources.getString(messageId),
        Snackbar.LENGTH_LONG
    )

    snackBar.duration = SNACKBAR_MAX_LINES * Snackbar.LENGTH_LONG

    snackBar.setBackgroundTint(
        ContextCompat.getColor(
            this.context,
            backgroundColor
        )
    )
}