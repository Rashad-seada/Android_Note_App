package com.example.noteapp.features.note.domain.repo

import com.example.noteapp.features.note.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepo {

    fun getNotes() : Flow<List<Note>>

    suspend fun getNoteById(int: Int) : Note?

    suspend fun deleteNote(note: Note)

    suspend fun addNote(note: Note)

}