package com.example.noteapp.features.note.domain.usecase

import com.example.noteapp.features.note.domain.models.Note
import com.example.noteapp.features.note.domain.repo.NoteRepo

class GetNoteUsecase(
    private val repo: NoteRepo,
) {

    suspend operator fun invoke(id : Int) : Note? {
        return repo.getNoteById(id)
    }
}