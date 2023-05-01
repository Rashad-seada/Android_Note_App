package com.example.noteapp.features.note.domain.usecase

import com.example.notes.features.note_feature.domain.usecase.AddNoteUsecase
import com.example.notes.features.note_feature.domain.usecase.DeleteNoteUsecase
import com.example.notes.features.note_feature.domain.usecase.GetNotesUsecase

data class NoteUsecases(
    val getNotesUsecase: GetNotesUsecase,
    val deleteNoteUsecase: DeleteNoteUsecase,
    val addNoteUsecase: AddNoteUsecase,
    val getNoteUsecases: GetNoteUsecase,
)
