package com.ncz.desafio_mobile_android.domain.utils

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class Alert {

    fun alertError(activity: Activity, title: String?, message: String?, actionOk: String?, listener: DialogInterface.OnClickListener?
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(message)
        builder.setTitle(title)
            .setPositiveButton(actionOk, listener)
        val alertDialog = builder.create()
        alertDialog.show()

    }
}