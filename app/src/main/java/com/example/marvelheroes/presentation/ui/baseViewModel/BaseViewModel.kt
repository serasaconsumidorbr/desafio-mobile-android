package com.example.marvelheroes.presentation.ui.baseViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.blackbook.v2.data.DispatchersProvider
import com.example.marvelheroes.data.retrofit.ErrorObject
import com.example.marvelheroes.data.retrofit.MessageGenericObject
import com.example.marvelheroes.data.retrofit.ResultWrapper
import com.example.marvelheroes.data.retrofit.RetrofitServiceGeneric
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val job: Job = SupervisorJob(),
    private val dispatchers: DispatchersProvider = DispatchersProvider(),
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job

    val ioCoroutineContext = dispatchers.io + job

    val message = MutableLiveData<Event<MessageGenericObject>>()

    val showLoading = MutableLiveData<Event<Boolean>>().apply {
        value = Event(false)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    open fun <PARAM, RESULT> exec(
        param: PARAM,
        service: RetrofitServiceGeneric<PARAM, RESULT>,
        onSuccessResult: ((value: RESULT) -> Unit),
        onError: ((statusCode: Int?, error: MessageGenericObject?, throwable: Throwable) -> MessageGenericObject?)? = null,
        showLoadingFlag: Boolean = true,
        shouldShowErrorMessage: Boolean = true
    ) {
        launch {
            showLoadingWithFlag(showLoadingFlag)
            try {
                proccessResult(service, param, onSuccessResult, onError, shouldShowErrorMessage)
            } catch (e: Exception) {
                errorNotResolve(e)
            } finally {
                hideLoadingWithFlag(showLoadingFlag)
            }
        }
    }

    private suspend fun <PARAM, RESULT> proccessResult(
        interactor: RetrofitServiceGeneric<PARAM, RESULT>,
        param: PARAM, onSuccessResult: ((value: RESULT) -> Unit)?,
        onError: ((statusCode: Int?, error: MessageGenericObject?, throwable: Throwable) -> MessageGenericObject?)?,
        shouldShowErrorMessage: Boolean
    ): RESULT? =
        when (val result = withContext(coroutineContext) {
            interactor(param)
        }) {
            is ResultWrapper.Success -> onResolveSuccess(onSuccessResult, result)
            is ResultWrapper.Failure -> {
                onResolveError(onError, result, shouldShowErrorMessage)
                null
            }
        }

    private fun <RESULT> onResolveSuccess(onSuccessResult: ((value: RESULT) -> Unit)?, result: ResultWrapper.Success<RESULT>): RESULT {
        onSuccessResult?.invoke(result.value)
        return result.value
    }

    private fun onResolveError(
        onError: ((statusCode: Int?, error: MessageGenericObject?, throwable: Throwable) -> MessageGenericObject?)?,
        result: ResultWrapper.Failure,
        shouldShowErrorMessage: Boolean
    ) {
        val error = onError?.invoke(result.code, result.error, result.throwable) ?: result.error
        if (shouldShowErrorMessage) message.value = Event(error)
    }

    private fun errorNotResolve(e: Exception) {
        Timber.e("Error not mapped")
        Timber.e(e)
        message.value = Event(ErrorObject(e))
    }

    private fun showLoadingWithFlag(showLoadingFlag: Boolean) {
        if (showLoadingFlag) {
            showLoading()
        }
    }

    private fun hideLoadingWithFlag(showLoadingFlag: Boolean) {
        if (showLoadingFlag) {
            hideLoading()
        }
    }

    open fun showLoading() {
        showLoading.value = Event(true)
    }

    open fun hideLoading() {
        showLoading.value = Event(false)
    }

}