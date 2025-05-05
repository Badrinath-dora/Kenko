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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doit.R

@Composable
fun onboardingStepTwoScreen(onBack: () -> Unit, onNext: () -> Unit) {
    var selectedGoal by remember { mutableStateOf("") }
    var timelineValue by remember { mutableStateOf("4") }
    var selectedUnit by remember { mutableStateOf("Weeks") }

    val goalOptions = listOf(
        "Lose fat" to R.drawable.ic_fat_loss,
        "Build muscle" to R.drawable.ic_muscle,
        "Body recomposition" to R.drawable.ic_recomposition,
        "Improve strength" to R.drawable.ic_strength
    )

    OnboardingScaffold(step = 2, onBack = onBack, onContinue = onNext) {
        Spacer(modifier = Modifier.height(8.dp))

        // Single-line heading with colored "Fitness"
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("Define Your ")
                }
                withStyle(style = SpanStyle(color = Color(0xFF4EC5D6))) {
                    append("Fitness Goals")
                }
            }, fontSize = 20.sp, fontWeight = FontWeight.Bold
        )


        Text(
            text = "Set your goal, choose your pace, and begin.",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Goal cards
        goalOptions.forEach { (goal, icon) ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (goal == selectedGoal) Color(0xFF4EC5D6) else Color(0xFF1C1C1E))
                .clickable { selectedGoal = goal }
                .padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = goal, color = Color.White, fontWeight = FontWeight.Medium)
                }
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
            OutlinedTextField(
                value = timelineValue,
                onValueChange = { timelineValue = it },
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
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

            toggleButton(selectedUnit) { selectedUnit = it }

            Spacer(modifier = Modifier.width(12.dp))

            // Vertical Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(32.dp)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Calendar Icon

            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Calendar",
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Optimal timeline set. Adjust if needed.",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
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
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(unit)
            }
        }
    }
}
