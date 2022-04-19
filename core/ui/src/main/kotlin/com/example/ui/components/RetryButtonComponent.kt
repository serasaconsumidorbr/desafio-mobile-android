package com.example.ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ui.R

@Composable
fun RetryButtonComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Button(
    onClick = onClick,
    modifier = modifier
) {
    Text(text = stringResource(id = R.string.try_again))
}