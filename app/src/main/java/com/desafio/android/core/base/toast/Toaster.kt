package com.desafio.android.core.base.toast

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Button
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.desafio.android.core.standalone.getActivity

class Toaster {
    fun doShowToast(text: String) {
        getActivity()?.runOnUiThread {
            Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show()
        }
    }

    fun doShowDialog(
        title: String,
        text: String,
        positiveText: String,
        negativeText: String? = null,
        onPositiveButtonClicked: (DialogInterface) -> Unit,
        onNegativeButtonClicked: ((DialogInterface) -> Unit)? = null,
        positiveButtonColor: Int = Color.Black.toArgb(),
        negativeButtonColor: Int = Color.Black.toArgb()
    ) {
        getActivity()?.runOnUiThread {
            var alert = AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton(
                    positiveText
                ) { dialog, _ ->
                    onPositiveButtonClicked(dialog)
                }

            if (negativeText != null && onNegativeButtonClicked != null) {
                alert = alert.setNegativeButton(
                    negativeText
                ) { dialog, _ ->
                    onNegativeButtonClicked(dialog)
                }
            }

            val dialog = alert.show()

            val positiveButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setTextColor(positiveButtonColor)

            val negativeButton: Button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            negativeButton.setTextColor(negativeButtonColor)
        }
    }
}