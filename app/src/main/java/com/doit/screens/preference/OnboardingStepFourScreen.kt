package com.doit.screens.preference

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doit.R

@Composable
fun onboardingStepFourScreen(onBack: () -> Unit, onNext: () -> Unit) {
    var selectedPreference by remember { mutableStateOf("") }
    var dietaryRestriction by remember { mutableStateOf(TextFieldValue("")) }
    var injuryAnswer by remember { mutableStateOf("") }

    val preferences = listOf("Vegetarian", "Non vegetarian", "Vegan", "Custom")
    val injuryOptions = listOf("Yes", "No")

    OnboardingScaffold(step = 4, onBack = onBack, onContinue = onNext) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            buildAnnotatedString {
                append("Customize Your ")
                withStyle(SpanStyle(color = Color(0xFF4EC5D6))) {
                    append("Health & Lifestyle")
                }
            }, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
        )

        Text(
            text = "Tailor your plan to fit your body and needs.",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Nutrition preference",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column {
            preferences.chunked(2).forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    row.forEach { pref ->
                        val isSelected = selectedPreference == pref
                        val isCustom = pref == "Custom"

                        Box(modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) Color(0xFF4EC5D6) else Color.Black)
                            .then(if (!isSelected) {
                                if (isCustom) {
                                    Modifier.drawBehind {
                                        drawRoundRect(
                                            color = Color.White,
                                            size = size,
                                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                                                10.dp.toPx()
                                            ),
                                            style = Stroke(
                                                width = 1.dp.toPx(),
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(10f, 10f)
                                                )
                                            )
                                        )
                                    }
                                } else {
                                    Modifier.border(
                                        1.dp, Color.White, RoundedCornerShape(10.dp)
                                    )
                                }
                            } else Modifier)
                            .clickable { selectedPreference = pref },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = pref,
                                color = if (isSelected) Color.Black else Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

        }

        if (selectedPreference == "Custom") {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Specify if any dietary restriction",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = dietaryRestriction,
                onValueChange = { dietaryRestriction = it },
                placeholder = { Text("Text", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.DarkGray,
                    unfocusedContainerColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "(Lactose free, Gluten free)", fontSize = 12.sp, color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Do you have any existing injuries?",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))

        var injuryDetails by remember { mutableStateOf(TextFieldValue("")) }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            injuryOptions.forEach { option ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (injuryAnswer == option) Color(0xFF4EC5D6) else Color(
                            0xFF1C1C1E
                        )
                    )
                    .clickable { injuryAnswer = option }
                    .padding(horizontal = 14.dp, vertical = 12.dp)) {
                    Text(
                        text = option,
                        color = if (injuryAnswer == option) Color.Black else Color.White,
                        fontWeight = FontWeight.Medium
                    )

                    if (option == "Yes" && injuryAnswer == "Yes") {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Specify the injury",
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(value = injuryDetails,
                            onValueChange = { injuryDetails = it },
                            placeholder = { Text("Text", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedContainerColor = Color.DarkGray,
                                unfocusedContainerColor = Color.DarkGray,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true
                        )
                    }
                }
            }
        }
    }
}
