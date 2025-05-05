package com.doit.screens.preference

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import java.util.Locale

@Composable
fun OnboardingScaffold(
    step: Int,
    totalSteps: Int = 5,
    onBack: () -> Unit,
    onContinue: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val view = LocalView.current
    val density = LocalDensity.current
    val topInsetPadding = with(density) {
        ViewCompat.getRootWindowInsets(view)
            ?.getInsets(WindowInsetsCompat.Type.statusBars())?.top?.toDp() ?: 0.dp
    }
    val topPadding = topInsetPadding + 24.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 24.dp, end = 24.dp, top = topPadding, bottom = 32.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.clickable { onBack() })
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$step/$totalSteps", color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(totalSteps) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(
                            color = if (index < step) Color.Cyan else Color.Gray,
                            shape = RoundedCornerShape(50)
                        )
                )
                if (index != totalSteps - 1) Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()), content = content
        )

        val buttonLabel = if (step == totalSteps) "Finish" else "Continue"

        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4EC5D6))
        ) {
            Text(text = buttonLabel, color = Color.Black)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownField(
    label: String, value: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val options = when (label) {
        "Gender" -> listOf("Male", "Female", "Other")
        "Height" -> (140..220).map { "$it cm" }
        "Weight", "Goal weight" -> (30..150).map { "$it kg" }
        else -> listOf("Text")
    }

    Column(modifier = modifier.padding(vertical = 6.dp)) {
        Text(
            text = label,
            color = Color.LightGray,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = value.ifEmpty { "Select" },
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                interactionSource = interactionSource,
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
                )
            )

            val maxVisibleItems = 5
            val isScrollable = label in listOf("Height", "Weight", "Goal weight")
            val dropdownHeight = if (isScrollable) (maxVisibleItems * 48).dp else Dp.Unspecified

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.heightIn(max = dropdownHeight)
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(text = { Text(selectionOption, color = Color.White) },
                        onClick = {
                            onValueChange(selectionOption)
                            expanded = false
                        })
                }
            }
        }
    }
}



