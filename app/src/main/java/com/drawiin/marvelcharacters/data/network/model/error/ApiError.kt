package com.drawiin.marvelcharacters.data.network.model.error


import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("code")
    val code: Any?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("message")
    val message: String?
)