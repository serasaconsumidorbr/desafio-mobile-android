package com.desafio.android.ui.screen.home.content.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desafio.android.R
import com.desafio.android.ui.theme.MaterialTypography

@Composable
fun MarvelCharacterStatisticIcon(
    icon: Int,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = icon),
            contentDescription = "Icon"
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            style = MaterialTypography.Poppins.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            ),
            color = Color.White
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MarvelCharacterStatisticIconPreview() {
    MarvelCharacterStatisticIcon(
        icon = R.drawable.ic_comics,
        text = "1"
    )
}