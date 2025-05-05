package com.doit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doit.R
import com.doit.screens.home.HomeScreen
import com.doit.screens.meals.MealsScreen
import com.doit.screens.profile.ProfileScreen
import com.doit.screens.workout.WorkoutScreen

@Composable
fun MainHomeWithBottomNav() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(containerColor = Color(0xFF121212), bottomBar = {
        BottomNavigationBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
    }) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> WorkoutScreen()
                2 -> MealsScreen()
                3 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val items = listOf("Home", "Workout", "Meals", "Profile")
    val iconsSelected = listOf(
        R.drawable.ic_home_selected,
        R.drawable.ic_workout_selected,
        R.drawable.ic_meals_selected,
        R.drawable.ic_profile_selected
    )
    val iconsUnselected = listOf(
        R.drawable.ic_home_unselected,
        R.drawable.ic_workout_unselected,
        R.drawable.ic_meals_unselected,
        R.drawable.ic_profile_unselected
    )

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF141414).copy(alpha = 0.9f),
            Color(0xFF032329).copy(alpha = 0.34f),
            Color(0xFF124F5C).copy(alpha = 0.3f),
            Color(0xFF326672).copy(alpha = 0.4f)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp) // ✅ Custom height
            .background(brush = gradient)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            items.forEachIndexed { index, label ->
                NavigationBarItem(selected = selectedTab == index,
                    onClick = { onTabSelected(index) },
                    icon = {
                        Image(
                            painter = painterResource(
                                id = if (selectedTab == index) iconsSelected[index] else iconsUnselected[index]
                            ), contentDescription = label, modifier = Modifier.size(22.dp)
                        )
                    },
                    label = {
                        Text(
                            text = label,
                            fontSize = 12.sp,
                            color = if (selectedTab == index) Color.White else Color.Gray
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}



