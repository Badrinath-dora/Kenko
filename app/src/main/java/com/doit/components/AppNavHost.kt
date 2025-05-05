package com.doit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doit.screens.login.loginScreen
import com.doit.screens.preference.onboardingStepFiveScreen
import com.doit.screens.preference.onboardingStepFourScreen
import com.doit.screens.preference.onboardingStepOneScreen
import com.doit.screens.preference.onboardingStepThirdScreen
import com.doit.screens.preference.onboardingStepTwoScreen

@Composable
fun appNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            loginScreen(navController)
        }

        composable("onboarding1") {
            onboardingStepOneScreen(onBack = {
                navController.navigate("login") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            }, onNext = { navController.navigate("onboarding2") })
        }

        composable("onboarding2") {
            onboardingStepTwoScreen(onBack = { navController.popBackStack() },
                onNext = { navController.navigate("onboarding3") })
        }
        composable("onboarding3") {
            onboardingStepThirdScreen(onBack = { navController.popBackStack() },
                onNext = { navController.navigate("onboarding4") } // or next screen
            )
        }
        composable("onboarding4") {
            onboardingStepFourScreen(onBack = { navController.popBackStack() }, onNext = {
                navController.navigate("onboarding5")
            })
        }
        composable("onboarding5") {
            onboardingStepFiveScreen(
                onBack = { navController.popBackStack() },
                onFinish = {
                    navController.navigate("mainHome") {
                        popUpTo("onboarding1") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("mainHome") {
            MainHomeWithBottomNav()
        }

    }
}
