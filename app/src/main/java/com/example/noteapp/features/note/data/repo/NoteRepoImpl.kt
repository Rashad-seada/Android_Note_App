package com.example.noteapp.features.note.data.repo

import com.example.noteapp.features.note.domain.models.Note
import com.example.noteapp.features.note.domain.repo.NoteRepo
import com.example.notes.features.note_feature.data.data_source.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl(private val dao: NoteDao) : NoteRepo {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id = id)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note = note)
    }

    override suspend fun addNote(note: Note) {
        return dao.insertNote(note = note)
    }

}