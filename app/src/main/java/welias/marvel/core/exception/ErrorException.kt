package welias.marvel.core.exception

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class ErrorException : Exception() {
    object NoConnectionError : ErrorException()
    object ServerError : ErrorException()
}

fun <T> Flow<T>.mapToCustomError(): Flow<T> = catch {
    throw it.getGenericOrDefaultThrowable()
}

private fun Throwable.getGenericOrDefaultThrowable() = when (this) {
    is SocketTimeoutException, is UnknownHostException -> ErrorException.NoConnectionError
    is HttpException -> ErrorException.ServerError
    else -> this
}
