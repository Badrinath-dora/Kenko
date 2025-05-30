package com.doit.screens.meals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MealsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Meals content goes here", color = Color.White, fontSize = 16.sp
        )
    }
}
