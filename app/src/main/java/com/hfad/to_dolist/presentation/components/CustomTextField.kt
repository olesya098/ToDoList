package com.hfad.to_dolist.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    title: String,
    onChange: (String) -> Unit
) {
    androidx.compose.material3.TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 15.dp, end = 15.dp, bottom = 4.dp)
            .background(Color.Transparent)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        value = value,
        onValueChange = { onChange(it) },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        placeholder = {
            Text(
                title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        },
    )

}