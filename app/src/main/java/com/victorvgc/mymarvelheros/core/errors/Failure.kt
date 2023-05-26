package com.victorvgc.mymarvelheros.core.errors

data class Failure(
    val exception: Exception? = null,
    val msg: String? = null,
    val code: FailureType
) {
    override fun toString(): String {
        return "Failure: ${code.name} - $msg"
    }
}

enum class FailureType {
    Unknown, EmptyResponse
}