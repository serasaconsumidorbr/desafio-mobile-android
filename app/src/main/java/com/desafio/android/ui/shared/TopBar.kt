package com.desafio.android.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desafio.android.R
import com.desafio.android.ui.theme.Gray2
import com.desafio.android.ui.theme.Gray3

@Composable
fun TopBar(
    color: Color = Gray2,
    onOptionsClicked: () -> Unit,
    onBellClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                color = color
            )
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .height(40.dp)
                    .align(
                        alignment = Alignment.Center
                    ),
                painter = painterResource(id = R.drawable.ic_marvel_logo),
                contentDescription = "Marvel Logo"
            )

            Image(
                modifier = Modifier
                    .clickable(
                        onClick = onOptionsClicked
                    )
                    .size(24.dp)
                    .align(
                        alignment = Alignment.CenterStart
                    ),
                painter = painterResource(id = R.drawable.ic_options),
                contentDescription = "Options Icon"
            )

            Image(
                modifier = Modifier
                    .clickable(
                        onClick = onBellClicked
                    )
                    .size(24.dp)
                    .align(
                        alignment = Alignment.CenterEnd
                    ),
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "Bell Icon"
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Gray3
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TopBarPreview() {
    TopBar(
        onOptionsClicked = {},
        onBellClicked = {}
    )
}