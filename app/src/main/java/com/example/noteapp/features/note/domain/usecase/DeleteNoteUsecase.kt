package com.example.notes.features.note_feature.domain.usecase

import com.example.noteapp.features.note.domain.models.Note
import com.example.noteapp.features.note.domain.repo.NoteRepo


class DeleteNoteUsecase (
    private val repo : NoteRepo
        ){

    suspend operator fun invoke(note : Note) {
        repo.deleteNote(note)
    }
}