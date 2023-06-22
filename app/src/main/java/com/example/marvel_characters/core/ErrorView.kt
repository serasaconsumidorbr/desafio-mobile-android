package com.example.marvel_characters.core

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.marvel_characters.databinding.CustomErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : FrameLayout(context, attrs, defStyle) {

    private val binding: CustomErrorViewBinding =
        CustomErrorViewBinding.inflate(LayoutInflater.from(context), this, true)

    /**
     * Set title and body message to the error view. If black generic texts are used.
     * @param title title of the error.
     * @param body message of the error.
     */
    fun setError(title: String, body: String) {
        if (title.isNotBlank()) {
            binding.errorTitle.text = title
        }
        if (body.isNotBlank()) {
            binding.errorBody.text = body
        }
    }

    /**
     * Call listener when retry buttons is pressed.
     * @param onRetryClicked action taken when button is pressed.
     */
    fun registerButtonAction(onRetryClicked: () -> Unit) {
        binding.errorRetryButton.setOnClickListener { onRetryClicked() }
    }
}