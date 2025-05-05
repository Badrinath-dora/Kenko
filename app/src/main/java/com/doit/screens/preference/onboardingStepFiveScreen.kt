package com.doit.screens.preference

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun onboardingStepFiveScreen(onBack: () -> Unit, onFinish: () -> Unit) {
    var selectedTime by remember { mutableStateOf("Morning") }
    var hours by remember { mutableStateOf("4") }
    var selectedCuisine by remember { mutableStateOf("Indian") }
    var mealBudget by remember { mutableStateOf("1000") }

    val timeOptions = listOf("Morning", "Evening", "Flexible")
    val cuisineOptions = listOf("Indian", "Italian", "Mexican", "Japanese", "Continental")

    OnboardingScaffold(step = 5, onBack = onBack, onContinue = onFinish) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            buildAnnotatedString {
                append("Final step, personalize your ")
                withStyle(style = SpanStyle(color = Color(0xFF4EC5D6))) {
                    append("Routine")
                }
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "Tailor meals and workouts to fit your lifestyle.",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Workouts:", color = Color(0xFF4EC5D6), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Text("Workout Time Preference", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            timeOptions.forEach { option ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (selectedTime == option) Color(0xFF4EC5D6) else Color.Black)
                        .then(
                            if (selectedTime != option)
                                Modifier.border(1.dp, Color.White, RoundedCornerShape(10.dp))
                            else Modifier
                        )
                        .clickable { selectedTime = option },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        color = if (selectedTime == option) Color.Black else Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Number of hours:", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = hours,
                onValueChange = { hours = it },
                modifier = Modifier.width(80.dp).height(50.dp),
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
            Spacer(modifier = Modifier.width(8.dp))
            Text("Hours", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Meals:", color = Color(0xFF4EC5D6), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Text("Specify your cuisine preference", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = selectedCuisine,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
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
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                cuisineOptions.forEach { cuisine ->
                    DropdownMenuItem(
                        text = { Text(cuisine, color = Color.White) },
                        onClick = {
                            selectedCuisine = cuisine
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text("Helps in meal plan & cuisine preferences", color = Color.Gray, fontSize = 12.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Text("Weekly Meal Budget:", color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = mealBudget,
                onValueChange = { mealBudget = it },
                modifier = Modifier.width(100.dp).height(50.dp),
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
            Spacer(modifier = Modifier.width(8.dp))
            Text("Rupees", color = Color.White)
        }
    }
}
