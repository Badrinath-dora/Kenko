package com.doit.screens.preference

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun onboardingStepTwoScreen(
    onBack: () -> Unit, onNext: () -> Unit
) {
    var selectedGoal by remember { mutableStateOf("") }
    var timelineValue by remember { mutableStateOf("4") }
    var selectedUnit by remember { mutableStateOf("Weeks") }

    val goalOptions = listOf(
        "Lose fat", "Build muscle", "Body recomposition", "Improve strength"
    )

    OnboardingScaffold(step = 2, onBack = onBack, onContinue = onNext) {
        Spacer(modifier = Modifier.height(8.dp))

        // Heading
        Text(
            text = "Define Your ", style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.White, fontWeight = FontWeight.Bold
            )
        )
        Text(
            buildAnnotatedString {
                append("Fitness ")
                withStyle(style = SpanStyle(color = Color.Cyan)) { append("Goals") }
            }, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            text = "Set your goal, choose your pace, and begin.",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        goalOptions.forEach { goal ->
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (goal == selectedGoal) Color.DarkGray else Color(0xFF1C1C1E))
                    .clickable { selectedGoal = goal }.padding(14.dp)
            ) {
                Text(
                    text = goal, color = Color.White, fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Goal timeline",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Number Input (simple)
            TextField(
                value = timelineValue,
                onValueChange = { timelineValue = it },
                modifier = Modifier.width(80.dp).height(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.DarkGray,
                    unfocusedContainerColor = Color.DarkGray,
                    disabledContainerColor = Color.DarkGray,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            toggleButton(selectedUnit, onUnitChange = { selectedUnit = it })

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier.size(42.dp).clip(CircleShape).background(Color.DarkGray)
                    .clickable { /* TODO: open calendar */ }, contentAlignment = Alignment.Center
            ) {
                Text("\uD83D\uDCC5", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Optimal timeline set. Adjust if needed.", fontSize = 12.sp, color = Color.Gray
        )
    }
}

@Composable
fun toggleButton(selectedUnit: String, onUnitChange: (String) -> Unit) {
    val units = listOf("Weeks", "Months")

    Row {
        units.forEach { unit ->
            Button(
                onClick = { onUnitChange(unit) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedUnit == unit) Color(0xFF4EC5D6) else Color.DarkGray,
                    contentColor = if (selectedUnit == unit) Color.Black else Color.White
                ),
                modifier = Modifier.padding(horizontal = 4.dp).height(50.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(unit)
            }
        }
    }
}
