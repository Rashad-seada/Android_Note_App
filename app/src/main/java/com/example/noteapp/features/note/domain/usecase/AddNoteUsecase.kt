package com.example.notes.features.note_feature.domain.usecase

import com.example.noteapp.features.note.domain.models.Note
import com.example.noteapp.features.note.domain.repo.NoteRepo


class AddNoteUsecase(
    private val repo : NoteRepo
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()){
            return
        }
        repo.addNote(note)
    }
}

class InvalidNoteException(message : String) : Exception(message)