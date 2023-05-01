package com.example.notes.features.note_feature.presentation.notes

import com.example.noteapp.features.note.domain.models.Note
import com.example.notes.features.note_feature.domain.util.NoteOrder
import com.example.notes.features.note_feature.domain.util.OrderType


data class NotesState(
    var notes : List<Note> = emptyList(),
    var notesOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    var isOrderSectionVisible : Boolean = false,
)
