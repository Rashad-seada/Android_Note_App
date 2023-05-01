package com.example.notes.features.note_feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.features.note.domain.models.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase  : RoomDatabase() {

    abstract val noteDao : NoteDao

    companion object {
        val DATABASE_NAME = "notes_db"
    }
}