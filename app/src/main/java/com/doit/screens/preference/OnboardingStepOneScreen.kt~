package com.doit.screens.preference

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun onboardingStepOneScreen(onNext: () -> Unit, onBack: () -> Unit) {
    var gender by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var goalWeight by remember { mutableStateOf("") }

    OnboardingScaffold(step = 1, onBack = onBack, onContinue = onNext) {
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = buildAnnotatedString {
                append("Let's Get to ")
                withStyle(SpanStyle(color = Color.Cyan)) { append("Know You") }
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "Share your basics to personalize your experience.",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        CustomDropdownField("Gender", gender) { gender = it }
        Spacer(modifier = Modifier.height(12.dp))
        CustomDropdownField("Date of Birth", dob) { dob = it }
        Spacer(modifier = Modifier.height(12.dp))
        CustomDropdownField("Height", height) { height = it }
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            CustomDropdownField("Weight", weight, modifier = Modifier.weight(1f)) { weight = it }
            Spacer(modifier = Modifier.width(12.dp))
            CustomDropdownField("Goal weight", goalWeight, modifier = Modifier.weight(1f)) { goalWeight = it }
        }
    }
}
