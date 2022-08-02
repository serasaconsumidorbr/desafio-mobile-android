package com.developer.marvel.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.developer.marvel.R
import com.google.android.material.snackbar.Snackbar

fun showError(view: View, message: String?): Snackbar {
    val snackbar = Snackbar.make(
        view, message ?: "Houve uma falha inesperada",
        Snackbar.LENGTH_LONG
    )

    val snackBarView = snackbar.view
    snackBarView.setBackgroundColor(
        ContextCompat.getColor(
            view.context,
            R.color.ic_launcher_background
        )
    )
    snackbar.show()

    return snackbar
}

fun showInfo(view: View, message: String?): Snackbar {
    val snackbar = Snackbar.make(
        view, "Estamos trabalhando...",
        Snackbar.LENGTH_LONG
    )

    val snackBarView = snackbar.view
    snackBarView?.setBackgroundColor(
        ContextCompat.getColor(
            view.context,
            R.color.md_theme_light_secondary,
        )
    )
    snackbar.show()

    return snackbar
}