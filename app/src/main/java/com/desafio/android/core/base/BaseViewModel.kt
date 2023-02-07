package com.desafio.android.core.base

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.desafio.android.R
import com.desafio.android.core.base.execution.CaseExecution
import com.desafio.android.core.base.navigation.Navigator
import com.desafio.android.core.base.toast.Toaster
import com.desafio.android.core.standalone.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<Interaction, ScreenState> : ViewModel() {
    abstract val state: ScreenState

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var toaster: Toaster

    fun interact(interaction: Interaction) {
        handleUserInteraction(interaction)
    }

    abstract fun handleUserInteraction(interaction: Interaction)

    open fun handleNavigateBack() {
        navigator.onBackPressed()
    }

    fun handleNavigateToInteraction(
        route: String,
        arguments: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: androidx.navigation.Navigator.Extras? = null
    ) {
        navigator.navigateTo(
            route = route,
            arguments = arguments,
            navOptions = navOptions,
            navigatorExtras = navigatorExtras
        )
    }

    fun handleStateVisibility(visible: Boolean, state: (Boolean) -> Unit) {
        state(visible)
    }

    open fun handleError(throwable: Throwable) {
        Log.e(this.javaClass.simpleName, throwable.localizedMessage, throwable)

        toaster.doShowDialog(
            title = getString(R.string.general_error),
            text = throwable.localizedMessage,
            positiveText = getString(R.string.general_ok),
            onPositiveButtonClicked = {
                it.dismiss()
            }
        )
    }

    private fun suspendScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }

    fun <T : Any> execute(
        case: suspend () -> T,
        execution: CaseExecution? = null,
        onResult: suspend (T) -> Unit
    ) {
        suspendScope {
            execution?.setAsLoading()
            val result = case()
            onResult(result)
            execution?.setAsNotLoading()
        }
    }
}