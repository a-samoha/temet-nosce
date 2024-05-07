package com.temetnosce.sadhana.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SadhanaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = SadhanaTypography,
        content = content
    )
}