package welias.marvel.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import welias.marvel.databinding.CustomViewToolbarBinding

class ToolbarCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = CustomViewToolbarBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setupIconsVisibility(backIsVisible: Boolean, searchIsVisible: Boolean) {
        viewBinding.back.isVisible = backIsVisible
        viewBinding.search.isVisible = searchIsVisible
    }

    fun backListener(callback: () -> Unit) {
        viewBinding.back.setOnClickListener {
            callback()
        }
    }

    fun searchListener(callback: () -> Unit) {
        viewBinding.search.setOnClickListener {
            callback()
        }
    }
}
