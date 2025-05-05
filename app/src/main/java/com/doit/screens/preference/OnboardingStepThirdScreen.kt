package com.doit.screens.preference

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun onboardingStepThirdScreen(
    onBack: () -> Unit, onNext: () -> Unit
) {
    var selectedExperience by remember { mutableStateOf("Beginner") }
    var selectedActivityLevel by remember { mutableStateOf("") }

    val experienceLevels = listOf("Beginner", "Intermediate", "Advanced", "Expert")
    val activityLevels = listOf(
        Triple("Lightly active", "Occasional walks or light movement", R.drawable.ic_light_active),
        Triple(
            "Moderately active", "Regular exercise (3–4 days a week)", R.drawable.ic_moderate_active
        ),
        Triple(
            "Very active", "Intense workouts or physically demanding job", R.drawable.ic_very_active
        ),
        Triple("Athlete", "High-performance training or sports", R.drawable.ic_athlete),
    )

    OnboardingScaffold(step = 3, onBack = onBack, onContinue = onNext) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            buildAnnotatedString {
                append("Share your ")
                withStyle(style = SpanStyle(color = Color(0xFF4EC5D6))) {
                    append("Fitness Journey")
                }
            }, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White
        )

        Text(
            text = "Share your daily activity and experience level.",
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Experience level",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Experience level buttons in 2x2 grid
        Column {
            experienceLevels.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { label ->
                        Button(
                            onClick = { selectedExperience = label },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedExperience == label) Color(0xFF4EC5D6) else Color.Black,
                                contentColor = if (selectedExperience == label) Color.Black else Color.White
                            ),
                            border = if (selectedExperience == label) null
                            else ButtonDefaults.outlinedButtonBorder,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                        ) {
                            Text(label)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Activity level",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Activity level cards
// Activity level cards
        activityLevels.forEach { (title, desc, icon) ->
            val isSelected = selectedActivityLevel == title
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (isSelected) Color(0xFF4EC5D6) else Color(0xFF1C1C1E))
                .clickable { selectedActivityLevel = title }
                .padding(horizontal = 14.dp, vertical = 12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = if (isSelected) Color.Black else Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = title,
                            color = if (isSelected) Color.Black else Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = desc,
                            color = if (isSelected) Color.Black else Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
