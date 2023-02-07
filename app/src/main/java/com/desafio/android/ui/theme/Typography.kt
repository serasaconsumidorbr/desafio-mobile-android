package com.desafio.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.desafio.android.R

val Typography = Typography(
    defaultFontFamily = MaterialFontFamily.Poppins
)

class MaterialTextStyle {
    fun getStyle(fontFamily: FontFamily): TextStyle {
        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    }
}

private object MaterialFontFamily {
    val Poppins = FontFamily(
        Font(R.font.poppins_regular, weight = FontWeight.Normal),
        Font(R.font.poppins_black, weight = FontWeight.Black),
        Font(R.font.poppins_bold, weight = FontWeight.Bold),
        Font(R.font.poppins_light, weight = FontWeight.Light),
        Font(R.font.poppins_medium, weight = FontWeight.Medium),
        Font(R.font.poppins_thin, weight = FontWeight.Thin)
    )
}

object MaterialTypography {
    val Poppins = MaterialTextStyle().getStyle(MaterialFontFamily.Poppins)
}