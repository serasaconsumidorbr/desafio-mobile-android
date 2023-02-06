package com.example.marvelheroes.data.retrofit

import com.example.marvelheroes.data.extensions.asErrorObjectByJson
import retrofit2.HttpException

const val CODE_EXCEPTION_INIT = 0

abstract class MessageGenericObject {
    open var type: TypeMessage = TypeMessage.ERROR

    open var messageString: String? = null

    open var codeError: Int = CODE_EXCEPTION_INIT

}

class ErrorObject(ex: Exception? = null) : MessageGenericObject() {
    private var errorObject: ErrorModel? = ex?.asErrorObjectByJson()
    override var type: TypeMessage = TypeMessage.ERROR
    override var messageString: String? = errorObject?.getErrorMessage()
    override var codeError: Int = getCodeException(ex)

    private fun getCodeException(ex: Exception?): Int {
        return try {
            val httpException = ex as HttpException
            httpException.code()
        } catch (e: Exception) {
            CODE_EXCEPTION_INIT
        }
    }
}

enum class TypeMessage {
    ERROR,
    SUCCESS,
    EMPTY_ERROR
}