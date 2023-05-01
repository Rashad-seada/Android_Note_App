package com.example.noteapp.features.note.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLines: Int,
    hint: String,
    modifier: Modifier = Modifier,
) {

    OutlinedTextField(

        modifier = Modifier
            .fillMaxWidth()
            .then(
                modifier
            ),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        maxLines = maxLines,
        placeholder = {
            Text(text = hint, style = TextStyle(color = Color.Black))
        },
        textStyle = TextStyle(color = Color.Black),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}