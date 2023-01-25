package welias.marvel.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import welias.marvel.databinding.ErrorLayoutBinding

class ErrorComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = ErrorLayoutBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun tryAgain(callback: () -> Unit) {
        viewBinding.appCompatButton.setOnClickListener {
            callback()
        }
    }

    fun setupVisibility(value: Boolean) {
        viewBinding.container.isVisible = value
    }
}
