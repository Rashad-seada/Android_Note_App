package com.example.notes.features.note_feature.presentation.notes

import com.example.noteapp.features.note.domain.models.Note
import com.example.notes.features.note_feature.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note : Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
    data class AddNote(val note: Note) : NotesEvent()
    object GetNotes : NotesEvent()

}
