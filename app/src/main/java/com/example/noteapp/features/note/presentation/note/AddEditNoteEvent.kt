package com.example.noteapp.features.note.presentation.note

sealed class AddEditNoteEvent {

    object AddNote : AddEditNoteEvent()

    data class ChangeColor(val color: Int) : AddEditNoteEvent()

}
