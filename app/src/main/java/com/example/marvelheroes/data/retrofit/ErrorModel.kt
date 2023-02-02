package com.example.marvelheroes.data.retrofit

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("message")
    val message: List<String>? = null,
    @SerializedName("data")
    val data: Any? = null,
) : Parcelable {

    fun getErrorMessage() : String {
        var messageFormat = ""
        message?.forEach { messageError ->
           messageFormat =  "$messageFormat$messageError "
        }
        return messageFormat
    }

    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (success) 1 else 0)
        parcel.writeStringList(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorModel> {
        override fun createFromParcel(parcel: Parcel): ErrorModel {
            return ErrorModel(parcel)
        }

        override fun newArray(size: Int): Array<ErrorModel?> {
            return arrayOfNulls(size)
        }
    }

}
