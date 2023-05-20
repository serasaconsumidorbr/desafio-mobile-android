package br.com.marvelcomics.base.util

import androidx.annotation.StringRes

sealed class UiException : Exception() {
    class TimeoutException : UiException()
    class GenericApiException : UiException()
    class GenericUiException : UiException()
}