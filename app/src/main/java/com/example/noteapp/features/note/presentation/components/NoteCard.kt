package com.example.notes.features.note_feature.presentation.notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.features.note.domain.models.Note

@Preview(showBackground = true)
@Composable
fun NoteCard(
    note: Note = Note(
        title = "rashad",
        content = "this is content description on my note and i should write whats in my mind here",
        timeStamp = "6:23 AM",
        color = 0,
    ),
    selectedColor: Int = 0,
    onDeleteClick : ()-> Unit = {},
    onClick : ()-> Unit = {}

    ) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = 10.dp,
                horizontal = 10.dp
            ),
        color = Color(note.color),
        shape = RoundedCornerShape(10.dp)

    ) {

        Column(
            modifier = Modifier.fillMaxSize().clickable {
                onClick()
            }.padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = note.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 10,
            )

            IconButton(
                onClick = {
                          onDeleteClick()
                },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}