package br.com.maceda.marvel.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(
        requireContext(),
        msg,
        duration
    ).show()
}