package com.challenge.marvelcharacters.model

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("data")
    val data : Data
)
