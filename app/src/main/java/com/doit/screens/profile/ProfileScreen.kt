package com.doit.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414)), // ✅ Updated to match HomeScreen
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Profile content goes here", color = Color.White, fontSize = 16.sp
        )
    }
}
