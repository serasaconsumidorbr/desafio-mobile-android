package com.example.ui.components.spacers

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.example.ui.components.spacers.base.SpacerType
import com.example.ui.components.spacers.base.BaseSpacer

@Composable
fun VerticalSpacer(size: Dp? = null) {
    BaseSpacer(spacerType = SpacerType.VERTICAL, size = size)
}