package com.doit.screens.workout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doit.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WorkoutScreen() {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())

    val days = (0..6).map { offset ->
        if (offset != 0) calendar.add(Calendar.DAY_OF_YEAR, 1)
        val shortName = dayFormat.format(calendar.time)
        val date = dateFormat.format(calendar.time)
        val status = when (offset) {
            0 -> DayStatus.Completed
            1 -> DayStatus.Ongoing
            2 -> DayStatus.Locked("Unlocks tomorrow")
            else -> DayStatus.Locked("Unlocks in ${offset - 1} days")
        }
        DayInfo(shortName, date, status)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414))
            .padding(16.dp)
    ) {
        Text("Workouts", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Let's crush your goals, Badrinath!", color = Color.LightGray, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(days) { index, day ->
                val isSelected = day.status is DayStatus.Ongoing
                val isCompleted = day.status is DayStatus.Completed
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                        .border(
                            width = if (isCompleted) 1.dp else 0.dp,
                            color = if (isCompleted) Color(0xFF00E5A0) else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (isSelected) Color(0xFF4EC5D6) else Color(0xFF1C1C1E),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(day.shortName, color = Color.White, fontSize = 12.sp)
                    Text(
                        day.date,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        WorkoutCard(
            dayNumber = "Day 1", title = "Chest & Triceps", status = "Completed", isCompleted = true
        )

        WorkoutCard(
            dayNumber = "Day 2", title = "Back & Biceps", status = "Ongoing", isOngoing = true
        )

        WorkoutCard(
            dayNumber = "Day 3", title = "Legs & Core", lockMessage = "Unlocks tomorrow"
        )

        WorkoutCard(
            dayNumber = "Day 4", title = "Shoulders & Core", lockMessage = "Unlocks in 1 day"
        )
    }
}

@Composable
fun WorkoutCard(
    dayNumber: String,
    title: String,
    status: String? = null,
    isCompleted: Boolean = false,
    isOngoing: Boolean = false,
    lockMessage: String? = null
) {
    val backgroundColor = Color(0xFF1C1C1E)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isOngoing) Modifier.border(
                    1.dp, Color(0xFF4EC5D6), RoundedCornerShape(10.dp)
                ) else Modifier
            )
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .padding(16.dp)
            .padding(bottom = if (lockMessage == null) 0.dp else 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .widthIn(min = 70.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFF124F5C), Color(0xFF326672))
                        ), shape = RoundedCornerShape(100.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(dayNumber, fontSize = 12.sp, color = Color.White)
            }

            if (isCompleted) {
                Text("Completed", fontSize = 12.sp, color = Color(0xFF00E5A0))
            } else if (isOngoing) {
                Text("Ongoing", fontSize = 12.sp, color = Color.White)
            } else if (lockMessage != null) {
                Column(horizontalAlignment = Alignment.End) {
                    Image(
                        painter = painterResource(R.drawable.ic_lock),
                        contentDescription = "Lock",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(lockMessage, fontSize = 12.sp, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box {
            Column(modifier = Modifier.graphicsLayer {
                alpha = if (lockMessage != null) 0.3f else 1f
            }) {
                Text(title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.height(10.dp))

                if (!isCompleted) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            38.dp, Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconWithText(R.drawable.ic_time, "45 min")
                        IconWithText(R.drawable.ic_level, "Intermediate")
                        IconWithText(R.drawable.ic_equipment, "Body weight")
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (isOngoing) {
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4EC5D6)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text("View workout plan", color = Color.Black)
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun IconWithText(iconId: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = Color.LightGray, fontSize = 12.sp)
    }
}

data class DayInfo(val shortName: String, val date: String, val status: DayStatus)

sealed class DayStatus {
    object Completed : DayStatus()
    object Ongoing : DayStatus()
    data class Locked(val reason: String) : DayStatus()
}